package me.oganesson.civilization.common;

import me.oganesson.civilization.Tags;
import me.oganesson.civilization.loaders.CZWorldLoader;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class CommonProxy {

    public void preLoad() {
    }

    public void load() {
        CZWorldLoader.init();
    }

}
