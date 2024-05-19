package com.chromanyan.meaningfulmaterials.mixin;

import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketRecipe.class)
public class FireworkRocketRecipeMixin {

    @Unique
    private static final Ingredient COSMITE_INGREDIENT = Ingredient.of(MMItems.COSMITE.get());

    //  This is fun. We can't just store local variables, which means we have to iterate again from within the mixin.
    //  This iteration is solely to make sure that the player only inserted EXACTLY one cosmite into the recipe.
    @Redirect(method = "matches(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/world/level/Level;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Ingredient;test(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean test(Ingredient instance, ItemStack itemStack, CraftingContainer craftingContainer, Level level) {
        if (!(instance.getItems()[0].getItem() == Items.FIREWORK_STAR)) return instance.test(itemStack);
        if (instance.test(itemStack)) return true;
        if (!COSMITE_INGREDIENT.test(itemStack)) return false;

        int count = 0;
        for(int k = 0; k < craftingContainer.getContainerSize(); ++k) {
            if (COSMITE_INGREDIENT.test(craftingContainer.getItem(k))) {
                count++;
            }
        }

        return count == 1;
    }

    @Inject(method = "assemble(Lnet/minecraft/world/inventory/CraftingContainer;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    private void assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess, CallbackInfoReturnable<ItemStack> cir) {
        boolean hasCosmite = false;
        for (int j = 0; j < pContainer.getContainerSize(); ++j) {
            ItemStack itemstack1 = pContainer.getItem(j);
            if (itemstack1.isEmpty() || !COSMITE_INGREDIENT.test(itemstack1)) continue;
            hasCosmite = true;
            break;
        }
        if (!hasCosmite) return;

        ItemStack itemStack = cir.getReturnValue();
        CompoundTag compoundTag = itemStack.getOrCreateTagElement("Fireworks");

        compoundTag.putByte("Flight", (byte) (compoundTag.getByte("Flight") + 1));
        compoundTag.putBoolean("Cosmite", true);

        cir.setReturnValue(itemStack);
    }
}
