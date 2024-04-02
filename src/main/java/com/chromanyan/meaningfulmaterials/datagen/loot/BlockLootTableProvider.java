package com.chromanyan.meaningfulmaterials.datagen.loot;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

public class BlockLootTableProvider extends BlockLoot {

    @Nonnull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        //noinspection deprecation
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter((block) -> MeaningfulMaterials.MODID.equals(Registry.BLOCK.getKey(block).getNamespace()))
                .collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        dropSelf(MMBlocks.COSMITE_BLOCK.get());
    }
}
