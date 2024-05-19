package com.chromanyan.meaningfulmaterials.datagen.loot;

import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.stream.Collectors;

public class BlockLootTableProvider extends BlockLootSubProvider {

    protected BlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MMBlocks.BLOCKS_REGISTRY.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
    }

    @Override
    protected void generate() {
        dropSelf(MMBlocks.COSMITE_BLOCK.get());

        dropSelf(MMBlocks.INFERNIUM_BLOCK.get());
        dropSelf(MMBlocks.RAW_INFERNIUM_BLOCK.get());

        dropSelf(MMBlocks.COSMIC_LANTERN.get());

        add(MMBlocks.COSMITE_ORE.get(), block -> createOreDrop(block, MMItems.COSMITE.get()));
        add(MMBlocks.INFERNIUM_ORE.get(), block -> createOreDrop(block, MMItems.RAW_INFERNIUM.get()));

        add(MMBlocks.INFERNAL_FIRE.get(), noDrop());
    }
}
