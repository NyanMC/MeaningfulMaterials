package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMItemModels extends ItemModelProvider {

    public MMItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MeaningfulMaterials.MODID, existingFileHelper);
    }

    @SuppressWarnings("SameParameterValue")
    private void basicModel(String name) {
        this.singleTexture(name,
                mcLoc("item/generated"),
                "layer0",
                modLoc("item/" + name));
    }

    private void blockModel(String name) {
        this.withExistingParent(name, modLoc("block/" + name));
    }

    @Override
    protected void registerModels() {
        basicModel("cosmite");
        blockModel("cosmite_block");
        blockModel("cosmite_ore");
    }
}
