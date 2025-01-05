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

    public CZMetaItems() {
        setRegistryName("civilization:meta_item");
        setCreativeTab(GregTechAPI.TAB_GREGTECH);
    }

    public void registerSubItems() {
        STRUCTURE_TOOL = this.addItem(1, "structure.tool")
                .addComponents(StructureWriterBehavior.INSTANCE)
                .setCreativeTabs(GregTechAPI.TAB_GREGTECH_TOOLS);

        MATURED_CLAY = this.addItem(2, "matured_clay");
    }

}
