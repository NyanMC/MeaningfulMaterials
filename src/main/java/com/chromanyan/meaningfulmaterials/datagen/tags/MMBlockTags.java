package com.chromanyan.meaningfulmaterials.datagen.tags;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class MMBlockTags extends BlockTagsProvider {

    public MMBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, MeaningfulMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                MMBlocks.COSMITE_BLOCK.get(),
                MMBlocks.COSMITE_ORE.get(),
                MMBlocks.COSMIC_LANTERN.get(),
                MMBlocks.INFERNIUM_BLOCK.get(),
                MMBlocks.INFERNIUM_ORE.get(),
                MMBlocks.RAW_INFERNIUM_BLOCK.get()
        );
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                MMBlocks.COSMITE_BLOCK.get(),
                MMBlocks.COSMITE_ORE.get()
        );
        tag(BlockTags.NEEDS_IRON_TOOL).add(
                MMBlocks.INFERNIUM_BLOCK.get(),
                MMBlocks.INFERNIUM_ORE.get(),
                MMBlocks.RAW_INFERNIUM_BLOCK.get()
        );

        tag(BlockTags.INFINIBURN_OVERWORLD).add(MMBlocks.INFERNIUM_BLOCK.get(), MMBlocks.RAW_INFERNIUM_BLOCK.get(), MMBlocks.INFERNIUM_ORE.get());
        tag(BlockTags.INFINIBURN_NETHER).add(MMBlocks.INFERNIUM_BLOCK.get(), MMBlocks.RAW_INFERNIUM_BLOCK.get(), MMBlocks.INFERNIUM_ORE.get());
        tag(BlockTags.INFINIBURN_END).add(MMBlocks.INFERNIUM_BLOCK.get(), MMBlocks.RAW_INFERNIUM_BLOCK.get(), MMBlocks.INFERNIUM_ORE.get());

        tag(BlockTags.BEACON_BASE_BLOCKS).add(MMBlocks.COSMITE_BLOCK.get(), MMBlocks.INFERNIUM_BLOCK.get());

        tag(MMTags.Blocks.STORAGE_BLOCKS_COSMITE).add(MMBlocks.COSMITE_BLOCK.get());
        tag(MMTags.Blocks.STORAGE_BLOCKS_INFERNIUM).add(MMBlocks.INFERNIUM_BLOCK.get());
        tag(MMTags.Blocks.STORAGE_BLOCKS_RAW_INFERNIUM).add(MMBlocks.RAW_INFERNIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).add(MMBlocks.COSMITE_BLOCK.get(), MMBlocks.INFERNIUM_BLOCK.get(), MMBlocks.RAW_INFERNIUM_BLOCK.get());

        tag(MMTags.Blocks.ORES_COSMITE).add(MMBlocks.COSMITE_ORE.get());
        tag(MMTags.Blocks.ORES_INFERNIUM).add(MMBlocks.INFERNIUM_ORE.get());
        tag(Tags.Blocks.ORES).add(MMBlocks.COSMITE_ORE.get(), MMBlocks.INFERNIUM_ORE.get());
    }
}
