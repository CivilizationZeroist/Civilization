package me.oganesson.civilization;

import gregtech.GTInternalTags;
import me.oganesson.civilization.api.utils.CZLog;
import me.oganesson.civilization.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING
)
public class Civilization {

    @SidedProxy(
            modId = Tags.MOD_ID,
            serverSide = "me.oganesson.civilization.common.CommonProxy",
            clientSide = "me.oganesson.civilization.client.ClientProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        CZLog.LOGGER.info("Pre Initialing..");
        proxy.preLoad();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        CZLog.LOGGER.info("Initialing..");
        proxy.load();
    }

}
