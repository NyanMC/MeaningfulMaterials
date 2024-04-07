package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MMRecipes extends RecipeProvider {

    public MMRecipes(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @SuppressWarnings("SameParameterValue")
    private void packAndUnpack(@NotNull Consumer<FinishedRecipe> consumer, ItemLike unpacked, ItemLike packed, String name, String name2) {
        ShapelessRecipeBuilder.shapeless(unpacked, 9)
                .requires(packed)
                .unlockedBy("has_unpackable", has(unpacked))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name + "_unpack"));
        ShapedRecipeBuilder.shaped(packed, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', unpacked)
                .unlockedBy("has_unpackable", has(unpacked))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name2));
    }

    @SuppressWarnings("SameParameterValue")
    private void oreProcessing(@NotNull Consumer<FinishedRecipe> consumer, ItemLike ore, ItemLike result, String name, float xp, int time) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore), result, xp, time)
                .unlockedBy("has_ore", has(ore))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name + "_smelting"));

        oreBlasting(consumer, ore, result, name, xp, time / 2);
    }

    private void oreBlasting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike ore, ItemLike result, String name, float xp, int time) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ore), result, xp, time)
                .unlockedBy("has_ore", has(ore))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name + "_blasting"));
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        packAndUnpack(consumer, MMItems.COSMITE.get(), MMItems.COSMITE_BLOCK_ITEM.get(), "cosmite", "cosmite_block");

        ShapedRecipeBuilder.shaped(MMItems.COSMITE_BOOTS.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', MMItems.COSMITE.get())
                .unlockedBy("has_cosmite", has(MMItems.COSMITE.get()))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "cosmite_boots"));

        ShapelessRecipeBuilder.shapeless(MMItems.COSMIC_ARROW.get(), 6)
                .requires(MMItems.COSMITE.get())
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .unlockedBy("has_cosmite", has(MMItems.COSMITE.get()))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "cosmic_arrow"));

        ShapelessRecipeBuilder.shapeless(MMItems.COSMIC_LANTERN_ITEM.get(), 4)
                .requires(MMItems.COSMITE.get())
                .requires(Items.LANTERN)
                .requires(Items.LANTERN)
                .requires(Items.LANTERN)
                .requires(Items.LANTERN)
                .unlockedBy("has_cosmite", has(MMItems.COSMITE.get()))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "cosmic_lantern"));

        oreProcessing(consumer, MMItems.COSMITE_ORE_ITEM.get(), MMItems.COSMITE.get(), "cosmite", 1.5f, 200);

        oreBlasting(consumer, MMItems.RAW_INFERNIUM.get(), MMItems.INFERNIUM_INGOT.get(), "infernium", 1.5f, 200);
        oreBlasting(consumer, MMItems.INFERNIUM_ORE_ITEM.get(), MMItems.INFERNIUM_INGOT.get(), "infernium_from_ore", 1.5f, 200);
    }
}
