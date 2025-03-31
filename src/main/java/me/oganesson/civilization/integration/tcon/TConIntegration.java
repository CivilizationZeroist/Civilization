package me.oganesson.civilization.integration.tcon;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

public class TConIntegration {

    public static void preInit() {
        var blueSteel = basicIngotMaterial(Materials.BlueSteel);
        processStats(blueSteel);
        register(blueSteel);

        var hsse = basicIngotMaterial(Materials.HSSE);
        processStats(hsse);
        register(hsse);

        var wroughtIron = basicIngotMaterial(Materials.WroughtIron);
        processStats(wroughtIron);
        register(wroughtIron);

        var stainlessSteel = basicIngotMaterial(Materials.StainlessSteel);
        processStats(stainlessSteel);
        register(stainlessSteel);

        var tungstenSteel = basicIngotMaterial(Materials.TungstenSteel);
        processStats(tungstenSteel);
        register(tungstenSteel);

        var roseGold = basicIngotMaterial(Materials.RoseGold);
        processStats(roseGold);
        register(roseGold);

        var cobaltBrass = basicIngotMaterial(Materials.CobaltBrass);
        processStats(cobaltBrass);
        register(cobaltBrass);

        var naquadahAlloy = basicIngotMaterial(Materials.NaquadahAlloy);
        processStats(naquadahAlloy);
        register(naquadahAlloy);

        var redSteel = basicIngotMaterial(Materials.RedSteel);
        processStats(redSteel);
        register(redSteel);

        var sterlingSilver = basicIngotMaterial(Materials.SterlingSilver);
        processStats(sterlingSilver);
        register(sterlingSilver);

        var duranium = basicIngotMaterial(Materials.Duranium);
        processStats(duranium);
        register(duranium);

        var invar = basicIngotMaterial(Materials.Invar);
        processStats(invar);
        register(invar);

        var aluminium = basicIngotMaterial(Materials.Aluminium);
        processStats(aluminium);
        register(aluminium);

        var tungstenCarbide = basicIngotMaterial(Materials.TungstenCarbide);
        processStats(tungstenCarbide);
        register(tungstenCarbide);

        var titanium = basicIngotMaterial(Materials.Titanium);
        processStats(titanium);
        register(titanium);

        var neutronium = basicIngotMaterial(Materials.Neutronium);
        processStats(neutronium);
        register(neutronium);

        var vanadiumSteel = basicIngotMaterial(Materials.VanadiumSteel);
        processStats(vanadiumSteel);
        register(vanadiumSteel);

        var ultimet = basicIngotMaterial(Materials.Ultimet);
        processStats(ultimet);
        register(ultimet);

        var damascus = basicIngotMaterial(Materials.DamascusSteel);
        processStats(damascus);
        register(damascus);
    }

    private static TCMaterial basicIngotMaterial(Material material) {
        var tcMaterial = new TCMaterial(material);
        tcMaterial.setCastable(true);
        tcMaterial.setCraftable(false);
        tcMaterial.setFluid(material.getFluid(FluidStorageKeys.LIQUID));
        tcMaterial.setRepresentativeItem(new UnificationEntry(OrePrefix.ingot, material).toString());
        return tcMaterial;
    }

    public static void processStats(TCMaterial mat) {
        var toolProp = mat.material.getProperty(PropertyKey.TOOL);
        mat.addStats(new HeadMaterialStats(toolProp.getToolDurability(), toolProp.getToolSpeed(),
                toolProp.getToolAttackDamage(), toolProp.getToolHarvestLevel()));
        mat.addStats(new HandleMaterialStats(1.0f, toolProp.getToolDurability()));
        mat.addStats(new ExtraMaterialStats(toolProp.getToolDurability() / 10));
    }

    private static void register(TCMaterial material) {
        TinkerRegistry.addMaterial(material);
        TinkerRegistry.integrate(material);
    }

}
