package me.oganesson.civilization;

import gregtech.GTInternalTags;
import gregtech.api.util.Mods;
import gregtech.common.ConfigHolder;
import me.oganesson.civilization.api.utils.CZLog;
import me.oganesson.civilization.api.utils.DisplayUtil;
import me.oganesson.civilization.common.CommonProxy;
import me.oganesson.civilization.common.items.CZMetaItems;
import me.oganesson.civilization.integration.tcon.TConIntegration;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.opengl.Display;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING + "required-after:tconstruct"
)
public class Civilization {

    @SidedProxy(
            modId = Tags.MOD_ID,
            serverSide = "me.oganesson.civilization.common.CommonProxy",
            clientSide = "me.oganesson.civilization.client.ClientProxy"
    )
    private static CommonProxy proxy;

    public static ResourceLocation czId(String path) {
        return new ResourceLocation(Tags.MOD_ID, path);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        CZLog.LOGGER.info("Pre Initialing..");
        proxy.preLoad();

        CZMetaItems.register();

        if (Mods.TinkersConstruct.isModLoaded()) {
            TConIntegration.preInit();
        }
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        CZLog.LOGGER.info("Initialing..");
        proxy.load();
    }

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        CZLog.LOGGER.info("Constructing..");

        ConfigHolder.machines.enableHighTierSolars = true;
        ConfigHolder.machines.highTierContent = true;

        Display.setTitle(DisplayUtil.getTitle());
        DisplayUtil.setCustomWindowIcon();
    }

}
