package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MMTags {

    public static class Items {
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }

        public static final TagKey<Item> GEMS_COSMITE = forgeTag("gems/cosmite");

        /**
         * ItemEntities containing an item with this tag will float in the air.
         */
        public static final TagKey<Item> DEFIES_GRAVITY = ItemTags.create(new ResourceLocation(MeaningfulMaterials.MODID, "defies_gravity"));
    }

}
