package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMBlocks {
    public static final DeferredRegister<Block> BLOCKS_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MeaningfulMaterials.MODID);

    public static final RegistryObject<Block> COSMITE_BLOCK = BLOCKS_REGISTRY.register("cosmite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL))
    );
}
