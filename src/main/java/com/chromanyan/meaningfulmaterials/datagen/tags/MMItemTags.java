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
        copy(MMTags.Blocks.STORAGE_BLOCKS_INFERNIUM, MMTags.Items.STORAGE_BLOCKS_INFERNIUM);
        copy(MMTags.Blocks.STORAGE_BLOCKS_RAW_INFERNIUM, MMTags.Items.STORAGE_BLOCKS_RAW_INFERNIUM);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        copy(MMTags.Blocks.ORES_COSMITE, MMTags.Items.ORES_COSMITE);
        copy(MMTags.Blocks.ORES_INFERNIUM, MMTags.Items.ORES_INFERNIUM);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);

        tag(MMTags.Items.GEMS_COSMITE).add(MMItems.COSMITE.get());
        tag(Tags.Items.GEMS).add(MMItems.COSMITE.get());

        tag(MMTags.Items.INGOTS_INFERNIUM).add(MMItems.INFERNIUM_INGOT.get());
        tag(Tags.Items.INGOTS).add(MMItems.INFERNIUM_INGOT.get());

        tag(MMTags.Items.RAW_MATERIALS_INFERNIUM).add(MMItems.RAW_INFERNIUM.get());
        tag(Tags.Items.RAW_MATERIALS).add(MMItems.RAW_INFERNIUM.get());

        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(MMItems.COSMITE.get(), MMItems.INFERNIUM_INGOT.get());

        tag(MMTags.Items.DEFIES_GRAVITY).add(
                MMItems.COSMITE.get(),
                MMItems.COSMIC_ARROW.get(),
                MMItems.COSMITE_BOOTS.get(),
                MMItems.COSMITE_BLOCK_ITEM.get(),
                MMItems.COSMITE_ORE_ITEM.get(),
                MMItems.COSMIC_LANTERN_ITEM.get()
        );

        tag(ItemTags.ARROWS).add(MMItems.COSMIC_ARROW.get());

        tag(Tags.Items.ARMORS_BOOTS).add(MMItems.COSMITE_BOOTS.get());
    }
}
