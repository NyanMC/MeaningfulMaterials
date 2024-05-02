package com.chromanyan.meaningfulmaterials.jei;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class MMJeiPlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(MeaningfulMaterials.MODID);
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        simpleItemInfo(registration, MMItems.COSMITE.get(), "firework_boosting");
        simpleItemInfo(registration, MMItems.COSMITE_BLOCK_ITEM.get(), "falling_blocks_no_fall");
        simpleItemInfo(registration, MMItems.INFERNIUM_DUST.get(), "food_enhancing");
    }

    private void simpleItemInfo(@NotNull IRecipeRegistration registration, Item item, String name) {
        registration.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM_STACK, Component.translatable("jei.meaningfulmaterials." + name));
    }
}
