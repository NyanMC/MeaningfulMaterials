package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MMTags {



    public static class Items {
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        public static final TagKey<Item> GEMS_COSMITE = forgeTag("gems/cosmite");
        public static final TagKey<Item> STORAGE_BLOCKS_COSMITE = forgeTag("storage_blocks/cosmite");
        public static final TagKey<Item> ORES_COSMITE = forgeTag("ores/cosmite");

        public static final TagKey<Item> INGOTS_INFERNIUM = forgeTag("ingots/infernium");
        public static final TagKey<Item> STORAGE_BLOCKS_INFERNIUM = forgeTag("storage_blocks/infernium");
        public static final TagKey<Item> RAW_MATERIALS_INFERNIUM = forgeTag("raw_materials/infernium");
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_INFERNIUM = forgeTag("storage_blocks/raw_infernium");
        public static final TagKey<Item> ORES_INFERNIUM = forgeTag("ores/infernium");

        /**
         * ItemEntities containing an item with this tag will float in the air.
         */
        public static final TagKey<Item> DEFIES_GRAVITY = ItemTags.create(new ResourceLocation(MeaningfulMaterials.MODID, "defies_gravity"));
    }

    public static class Blocks {
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

        public static final TagKey<Block> STORAGE_BLOCKS_COSMITE = forgeTag("storage_blocks/cosmite");
        public static final TagKey<Block> ORES_COSMITE = forgeTag("ores/cosmite");

        public static final TagKey<Block> STORAGE_BLOCKS_INFERNIUM = forgeTag("storage_blocks/infernium");
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_INFERNIUM = forgeTag("storage_blocks/raw_infernium");
        public static final TagKey<Block> ORES_INFERNIUM = forgeTag("ores/infernium");
    }

}
