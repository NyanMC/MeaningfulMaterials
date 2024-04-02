package com.chromanyan.meaningfulmaterials.datagen.tags;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class MMItemTags extends ItemTagsProvider {

    public MMItemTags(DataGenerator p_126530_, BlockTagsProvider p_126531_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, MeaningfulMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(MMTags.Blocks.STORAGE_BLOCKS_COSMITE, MMTags.Items.STORAGE_BLOCKS_COSMITE);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        tag(MMTags.Items.GEMS_COSMITE).add(MMItems.COSMITE.get());
        tag(Tags.Items.GEMS).add(MMItems.COSMITE.get());
        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(MMItems.COSMITE.get());

        tag(MMTags.Items.DEFIES_GRAVITY).add(MMItems.COSMITE.get(), MMItems.COSMITE_BLOCK_ITEM.get());
    }
}
