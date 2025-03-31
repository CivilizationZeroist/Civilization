package me.oganesson.civilization.api.utils;

import me.oganesson.civilization.CZConfigHolder;
import me.oganesson.civilization.Civilization;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class DisplayUtil {

    public static String getTitle() {
        return "Civiliz'd | {version} | {type} ".replace("{version}", CZConfigHolder.packVersion).replace("{type}", CZConfigHolder.packType);
    }

    public static void setCustomWindowIcon() {
        var x16 = DisplayUtil.formatImagePath("x16");
        var x32 = DisplayUtil.formatImagePath("x32");
        var x256 = DisplayUtil.formatImagePath("x256");
        if (x16.isEmpty() || x32.isEmpty() || x256.isEmpty()) return;

        CZLog.LOGGER.info("Replacing Window Icons: 16x: {}, 32x: {}, 256x: {}.", x16, x32, x256);

        // From Random Patches (See Below)
        // https://github.com/TheRandomLabs/RandomPatches/blob/1.12/src/main/java/com/therandomlabs/randompatches/client/WindowIconHandler.java
        InputStream stream16 = null;
        InputStream stream32 = null;
        InputStream stream256 = null;
        var osX = Util.getOSType() == Util.EnumOS.OSX;

        try {
            stream16 = getResource(x16);
            stream32 = getResource(x32);

            if (osX) {
                stream256 = getResource(x256);
                Display.setIcon(new ByteBuffer[] {
                        readImageToBuffer(stream16, 16),
                        readImageToBuffer(stream32, 32),
                        readImageToBuffer(stream256, 256)
                });
            } else {
                Display.setIcon(new ByteBuffer[] {
                        readImageToBuffer(stream16, 16),
                        readImageToBuffer(stream32, 32)
                });
            }
        } catch (IOException ex) {
            CZLog.LOGGER.error("Failed to Read Custom Window Icon! Is the Path Correct?", ex);
        } finally {
            IOUtils.closeQuietly(stream16);
            IOUtils.closeQuietly(stream32);

            if (osX) {
                IOUtils.closeQuietly(stream256);
            }
        }
    }

    private static InputStream getResource(String path) {
        return Civilization.class.getClassLoader().getResourceAsStream(path);
    }

    private static ByteBuffer readImageToBuffer(InputStream stream, int dimensions)
            throws IOException {
        BufferedImage image = ImageIO.read(stream);

        if (image.getWidth() != dimensions || image.getHeight() != dimensions) {
            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();

            GraphicsDevice device = environment.getDefaultScreenDevice();

            GraphicsConfiguration gc = device.getDefaultConfiguration();

            BufferedImage resized = gc.createCompatibleImage(
                    dimensions,
                    dimensions,
                    image.getTransparency());

            Graphics2D graphics = resized.createGraphics();

            graphics.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            graphics.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            graphics.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.drawImage(image, 0, 0, dimensions, dimensions, null);

            graphics.dispose();

            image = resized;
        }

        int[] rgb = image.getRGB(0, 0, dimensions, dimensions, null, 0, dimensions);
        ByteBuffer buffer = ByteBuffer.allocate(rgb.length * 4);

        for (int i : rgb) {
            buffer.putInt(i << 8 | i >> 24 & 255);
        }

        buffer.flip();
        return buffer;
    }

    public static String formatImagePath(String size) {
        return CZConfigHolder.packType.equals("release") ? releaseIconPath(size) : nightlyIconPath(size);
    }

    private static String releaseIconPath(String size) {
        return String.format("assets/civilization/textures/icon/release/%s.png", size);
    }

    private static String nightlyIconPath(String size) {
        return String.format("assets/civilization/textures/icon/build/%s.png", size);
    }

}
