package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MMBlockStates extends BlockStateProvider {
    public MMBlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MeaningfulMaterials.MODID, existingFileHelper);
    }

    public ModelFile cubeAll(String name) {
        return models().cubeAll(name, modLoc("block/" + name));
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(MMBlocks.COSMITE_BLOCK.get());
        simpleBlock(MMBlocks.COSMITE_ORE.get());
    }
}
