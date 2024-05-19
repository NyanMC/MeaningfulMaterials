package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class MMRecipes extends RecipeProvider {

    public MMRecipes(PackOutput packOutput) {
        super(packOutput);
    }

    @SuppressWarnings("SameParameterValue")
    private void packAndUnpack(@NotNull Consumer<FinishedRecipe> consumer, ItemLike unpacked, ItemLike packed, String name, String name2) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, unpacked, 9)
                .requires(packed)
                .unlockedBy("has_unpackable", has(unpacked))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name + "_unpack"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, packed, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', unpacked)
                .unlockedBy("has_unpackable", has(unpacked))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name2));
    }

    @SuppressWarnings("SameParameterValue")
    private void oreProcessing(@NotNull Consumer<FinishedRecipe> consumer, ItemLike ore, ItemLike result, String name, float xp, int time) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore), RecipeCategory.MISC, result, xp, time)
                .unlockedBy("has_ore", has(ore))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name + "_smelting"));

        oreBlasting(consumer, ore, result, name, xp, time / 2);
    }

    private void oreBlasting(@NotNull Consumer<FinishedRecipe> consumer, ItemLike ore, ItemLike result, String name, float xp, int time) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ore), RecipeCategory.MISC, result, xp, time)
                .unlockedBy("has_ore", has(ore))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, name + "_blasting"));
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        packAndUnpack(consumer, MMItems.COSMITE.get(), MMItems.COSMITE_BLOCK_ITEM.get(), "cosmite", "cosmite_block");
        packAndUnpack(consumer, MMItems.INFERNIUM_INGOT.get(), MMItems.INFERNIUM_BLOCK_ITEM.get(), "infernium", "infernium_block");
        packAndUnpack(consumer, MMItems.RAW_INFERNIUM.get(), MMItems.RAW_INFERNIUM_BLOCK_ITEM.get(), "raw_infernium", "raw_infernium_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MMItems.COSMITE_BOOTS.get())
                .pattern("# #")
                .pattern("# #")
                .define('#', MMItems.COSMITE.get())
                .unlockedBy("has_cosmite", has(MMItems.COSMITE.get()))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "cosmite_boots"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, MMItems.COSMIC_ARROW.get(), 6)
                .requires(MMItems.COSMITE.get())
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .requires(Items.ARROW)
                .unlockedBy("has_cosmite", has(MMItems.COSMITE.get()))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "cosmic_arrow"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, MMItems.COSMIC_LANTERN_ITEM.get(), 4)
                .requires(MMItems.COSMITE.get())
                .requires(Items.LANTERN)
                .requires(Items.LANTERN)
                .requires(Items.LANTERN)
                .requires(Items.LANTERN)
                .unlockedBy("has_cosmite", has(MMItems.COSMITE.get()))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "cosmic_lantern"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MMItems.INFERNIUM_DUST.get())
                .requires(MMTags.Items.INGOTS_INFERNIUM)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_dust"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, MMItems.INFERNIUM_LIGHTER.get())
                .requires(MMTags.Items.INGOTS_INFERNIUM)
                .requires(Items.FLINT)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_lighter"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MMItems.INFERNIUM_SWORD.get())
                .pattern("#")
                .pattern("#")
                .pattern("s")
                .define('#', MMTags.Items.INGOTS_INFERNIUM)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_sword"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.INFERNIUM_SHOVEL.get())
                .pattern("#")
                .pattern("s")
                .pattern("s")
                .define('#', MMTags.Items.INGOTS_INFERNIUM)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.INFERNIUM_PICKAXE.get())
                .pattern("###")
                .pattern(" s ")
                .pattern(" s ")
                .define('#', MMTags.Items.INGOTS_INFERNIUM)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.INFERNIUM_AXE.get())
                .pattern("##")
                .pattern("#s")
                .pattern(" s")
                .define('#', MMTags.Items.INGOTS_INFERNIUM)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.INFERNIUM_HOE.get())
                .pattern("##")
                .pattern(" s")
                .pattern(" s")
                .define('#', MMTags.Items.INGOTS_INFERNIUM)
                .define('s', Tags.Items.RODS_WOODEN)
                .unlockedBy("has_infernium", has(MMTags.Items.INGOTS_INFERNIUM))
                .save(consumer, new ResourceLocation(MeaningfulMaterials.MODID, "infernium_hoe"));

        oreProcessing(consumer, MMItems.COSMITE_ORE_ITEM.get(), MMItems.COSMITE.get(), "cosmite", 1.5f, 200);

        oreBlasting(consumer, MMItems.RAW_INFERNIUM.get(), MMItems.INFERNIUM_INGOT.get(), "infernium", 1.5f, 200);
        oreBlasting(consumer, MMItems.INFERNIUM_ORE_ITEM.get(), MMItems.INFERNIUM_INGOT.get(), "infernium_from_ore", 1.5f, 200);
    }
}
