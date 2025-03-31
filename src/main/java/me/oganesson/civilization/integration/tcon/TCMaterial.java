package me.oganesson.civilization.integration.tcon;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.Material;

public class TCMaterial extends Material {

    public final gregtech.api.unification.material.Material material;

    public TCMaterial(gregtech.api.unification.material.Material material) {
        super(material.getName(), material.getMaterialRGB());
        this.material = material;
    }

    @Override
    public String getLocalizedName() {
        return Util.translate(material.getResourceLocation().getNamespace() + ".material." + material.getResourceLocation().getPath());
    }
}
