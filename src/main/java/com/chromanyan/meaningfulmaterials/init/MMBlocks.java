package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.content.block.CosmicLanternBlock;
import com.chromanyan.meaningfulmaterials.content.block.InfernalFireBlock;
import com.chromanyan.meaningfulmaterials.content.block.InferniumBlock;
import com.chromanyan.meaningfulmaterials.content.block.InferniumOreBlock;
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
    public static final RegistryObject<Block> COSMITE_ORE = BLOCKS_REGISTRY.register("cosmite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_PURPLE)
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 6.0F))
    );

    public static final RegistryObject<Block> INFERNIUM_BLOCK = BLOCKS_REGISTRY.register("infernium_block",
            () -> new InferniumBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .lightLevel(value -> 7)
                    .sound(SoundType.METAL))
    );
    public static final RegistryObject<Block> INFERNIUM_ORE = BLOCKS_REGISTRY.register("infernium_ore", InferniumOreBlock::new);

    public static final RegistryObject<Block> RAW_INFERNIUM_BLOCK = BLOCKS_REGISTRY.register("raw_infernium_block",
            () -> new InferniumBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .lightLevel(value -> 5))
    );

    public static final RegistryObject<Block> COSMIC_LANTERN = BLOCKS_REGISTRY.register("cosmic_lantern",
            CosmicLanternBlock::new);

    @SuppressWarnings("unused") // we don't need nor want to make a BlockItem for infernal fire
    public static final RegistryObject<Block> INFERNAL_FIRE = BLOCKS_REGISTRY.register("infernal_fire", InfernalFireBlock::new);
}
