package com.chromanyan.meaningfulmaterials.content.recipe;

import com.chromanyan.meaningfulmaterials.init.MMRecipeSerializers;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EnhanceFoodRecipe extends CustomRecipe {
    private static final Ingredient INFERNIUM_INGREDIENT = Ingredient.of(MMTags.Items.DUSTS_INFERNIUM);

    public EnhanceFoodRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(@NotNull CraftingContainer pContainer, @NotNull Level pLevel) {
        boolean hasInfernium = false;
        boolean hasFood = false;

        for(int j = 0; j < pContainer.getContainerSize(); ++j) {
            ItemStack itemstack = pContainer.getItem(j);
            if (itemstack.isEmpty()) continue;

            if (INFERNIUM_INGREDIENT.test(itemstack)) {
                if (hasInfernium) return false;
                hasInfernium = true;
            }

            if (itemstack.isEdible()) {
                if (hasFood) return false;
                // do we have to copy? idk if getOrCreateTag is a mutable operation but better to be safe
                if (itemstack.copy().getOrCreateTag().getBoolean("infernium_boosted")) return false;

                hasFood = true;
            }
        }

        return hasInfernium && hasFood;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer pContainer, @NotNull RegistryAccess pRegistryAccess) {
        ItemStack foodStack = null;
        for(int j = 0; j < pContainer.getContainerSize(); ++j) {
            ItemStack itemstack = pContainer.getItem(j);
            if (itemstack.isEmpty()) continue;

            if (itemstack.isEdible()) {
                foodStack = itemstack.copy();
                break;
            }
        }

        if (foodStack == null) {
            return ItemStack.EMPTY;
        }

        foodStack.getOrCreateTag().putBoolean("infernium_boosted", true);
        foodStack.setCount(1);

        return foodStack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeType.CRAFTING;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return MMRecipeSerializers.ENHANCE_FOOD.get();
    }
}
