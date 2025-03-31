package me.oganesson.civilization.loaders.recipes;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import me.oganesson.civilization.common.items.CZMetaItems;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.M;

public class CZMaterialTweaks {

    private static final OrePrefix[] remove = new OrePrefix[]{
            OrePrefix.ore,
            OrePrefix.oreEndstone,
            OrePrefix.oreNetherrack,
            OrePrefix.crushed,
            OrePrefix.dust,
            OrePrefix.dustImpure,
    };

    private static final OrePrefix[] retain = new OrePrefix[]{
            OrePrefix.crushedCentrifuged,
            OrePrefix.crushedPurified,
            OrePrefix.dustPure
    };

    public static void init() {
        for (MaterialRegistry registry : GregTechAPI.materialManager.getRegistries()) {
            for (Material material : registry.getAllMaterials()) {
                if (material.hasFluid()) {
                    if (material.hasProperty(PropertyKey.INGOT)) {
                        var fluid = material.getProperty(PropertyKey.FLUID).solidifiesFrom();
                        if (fluid != null) {
                            if (material.getBlastTemperature() > 1750) {
                                TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.ingotHot, material), TinkerSmeltery.castIngot, fluid, L);
                                TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.ingot, material), fluid, L);
                            } else {
                                if (!OreDictUnifier.get(OrePrefix.ingot, material).isEmpty()) {
                                    TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.ingot, material), TinkerSmeltery.castIngot, fluid, L);
                                    TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.ingot, material), fluid, L);
                                }

                                if (!OreDictUnifier.get(OrePrefix.nugget, material).isEmpty()) {
                                    TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.nugget, material), TinkerSmeltery.castNugget, fluid, L / 9);
                                    TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.nugget, material), fluid, L / 9);
                                }

                                if (!OreDictUnifier.get(OrePrefix.block, material).isEmpty()) {
                                    ItemStack blockStack = OreDictUnifier.get(OrePrefix.block, material);
                                    long materialAmount = OrePrefix.block.getMaterialAmount(material);
                                    TinkerRegistry.registerBasinCasting(blockStack, ItemStack.EMPTY, fluid, (int) (materialAmount * L / M));
                                    TinkerRegistry.registerMelting(blockStack, fluid, (int) (materialAmount * L / M));
                                }

                                if (!OreDictUnifier.get(OrePrefix.plate, material).isEmpty()) {
                                    TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.plate, material), TinkerSmeltery.castPlate, fluid, L);
                                    TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.plate, material), fluid, L);
                                }

                                if (!OreDictUnifier.get(OrePrefix.gear, material).isEmpty()) {
                                    TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.gear, material), TinkerSmeltery.castGear, fluid, L*4);
                                    TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.gear, material), fluid, L*4);
                                }

                                if (!OreDictUnifier.get(OrePrefix.gearSmall, material).isEmpty()) {
                                    TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.gearSmall, material), CZMetaItems.CAST_SMALL_GEAR.getStackForm(), fluid, L*2);
                                    TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.gearSmall, material), fluid, L*2);
                                }

                                if (!OreDictUnifier.get(OrePrefix.rotor, material).isEmpty()) {
                                    TinkerRegistry.registerTableCasting(OreDictUnifier.get(OrePrefix.rotor, material), CZMetaItems.CAST_ROTOR.getStackForm(), fluid, L*4);
                                    TinkerRegistry.registerMelting(OreDictUnifier.get(OrePrefix.rotor, material), fluid, L*4);
                                }
                            }
                        }
                    }

                    TinkerRegistry.registerSmelteryFuel(material.getFluid(1), material.getBlastTemperature() - 273);
                }

                if (material.hasProperty(PropertyKey.ORE)) {
                    OreProperty property = material.getProperty(PropertyKey.ORE);
                    Material smeltingResult = property.getDirectSmeltResult() != null ? property.getDirectSmeltResult() : material;

                    if (!smeltingResult.hasProperty(PropertyKey.BLAST)) {
                        for (OrePrefix prefix : remove) {
                            ModHandler.removeFurnaceSmelting(new UnificationEntry(prefix, material));
                        }
                    }


                    if (smeltingResult.hasFluid()) {
                        for (OrePrefix prefix : retain) {
                            var fluid = smeltingResult.getFluid();
                            TinkerRegistry.registerMelting(OreDictUnifier.get(prefix, material), fluid, smeltingResult.hasProperty(PropertyKey.INGOT) ? 144 : 666);
                        }
                    }
                }
            }
        }

        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Iron));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Gold));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Coal));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Lapis));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Diamond));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Emerald));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.Redstone));
        ModHandler.removeFurnaceSmelting(new UnificationEntry(OrePrefix.ore, Materials.NetherQuartz));


    }

}
