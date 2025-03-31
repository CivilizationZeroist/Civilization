package me.oganesson.civilization.loaders.recipes;

import me.oganesson.civilization.Tags;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID)
public class CZRecipeManager {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerLowest(RegistryEvent.Register<IRecipe> event) {
        CZMaterialTweaks.init();
    }

}
