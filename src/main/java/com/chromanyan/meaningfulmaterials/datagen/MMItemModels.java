package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMItemModels extends ItemModelProvider {

    public MMItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MeaningfulMaterials.MODID, existingFileHelper);
    }

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
        basicModel("cosmite_boots");
        basicModel("cosmic_arrow");
        basicModel("cosmic_lantern");

        blockModel("cosmite_block");
        blockModel("cosmite_ore");

        basicModel("infernium_ingot");
        basicModel("raw_infernium");
        basicModel("infernium_dust");
        basicModel("infernium_lighter");

        basicModel("infernium_sword");
        basicModel("infernium_shovel");
        basicModel("infernium_pickaxe");
        basicModel("infernium_axe");
        basicModel("infernium_hoe");

        blockModel("infernium_block");
        blockModel("infernium_ore");
        blockModel("raw_infernium_block");
    }
}
