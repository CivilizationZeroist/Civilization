package me.oganesson.civilization.loaders.recipes.tcon;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;

@Mod.EventBusSubscriber
public class TConRecipeTweaks {

    @SubscribeEvent
    public static void onTinkerRegister(TinkerRegisterEvent.MeltingRegisterEvent event) {
        for (ItemStack input : event.getRecipe().input.getInputs()) {
            if (OreDictUnifier.getPrefix(input) == OrePrefix.ore) {
                event.setCanceled(true);
            }

            if (OreDictUnifier.getPrefix(input) == OrePrefix.dustSmall || OreDictUnifier.getPrefix(input) == OrePrefix.dustTiny) {
                event.setCanceled(true);
            }
        }
    }

}
