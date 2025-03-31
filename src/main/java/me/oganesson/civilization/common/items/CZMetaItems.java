package me.oganesson.civilization.common.items;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import me.oganesson.civilization.common.items.behaviors.StructureWriterBehavior;

public class CZMetaItems extends StandardMetaItem {
    // Tools
    public static MetaItem<?>.MetaValueItem STRUCTURE_TOOL;

    // Materials
    public static MetaItem<?>.MetaValueItem MATURED_CLAY;

    // Casts
    public static MetaItem<?>.MetaValueItem CAST_SMALL_GEAR;
    public static MetaItem<?>.MetaValueItem CAST_ROTOR;

    private CZMetaItems() {
        setRegistryName("civilization:meta_item");
        setCreativeTab(GregTechAPI.TAB_GREGTECH);
    }

    public static void register() {
        new CZMetaItems();
    }

    public void registerSubItems() {
        STRUCTURE_TOOL = this.addItem(1, "structure.tool")
                .addComponents(StructureWriterBehavior.INSTANCE)
                .setCreativeTabs(GregTechAPI.TAB_GREGTECH_TOOLS);

        MATURED_CLAY = this.addItem(2, "matured_clay");

        CAST_SMALL_GEAR = this.addItem(3, "cast.small_gear");
        CAST_ROTOR = this.addItem(4, "cast.rotor");
    }

}
