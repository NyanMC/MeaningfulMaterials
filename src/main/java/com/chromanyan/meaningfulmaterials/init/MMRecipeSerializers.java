package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.content.recipe.EnhanceFoodRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MeaningfulMaterials.MODID);

    public static final RegistryObject<RecipeSerializer<EnhanceFoodRecipe>> ENHANCE_FOOD = RECIPE_SERIALIZERS_REGISTRY.register("enhance_food", () -> new SimpleCraftingRecipeSerializer<>(EnhanceFoodRecipe::new));
}
