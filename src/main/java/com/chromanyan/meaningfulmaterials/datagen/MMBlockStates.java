package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMBlockStates extends BlockStateProvider {
    public MMBlockStates(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MeaningfulMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(MMBlocks.COSMITE_BLOCK.get());
        simpleBlock(MMBlocks.COSMITE_ORE.get());
    }
}
