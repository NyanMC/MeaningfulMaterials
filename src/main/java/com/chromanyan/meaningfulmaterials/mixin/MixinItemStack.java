package com.chromanyan.meaningfulmaterials.mixin;

import com.chromanyan.meaningfulmaterials.init.MMToolMaterials;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@SuppressWarnings("UnreachableCode") // intellij is a stupid idiot
@Mixin(ItemStack.class)
public class MixinItemStack {

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void hurt(int amount, RandomSource randomSource, @Nullable ServerPlayer serverPlayer, CallbackInfoReturnable<Boolean> cir) {
        if (serverPlayer == null || !(serverPlayer.isOnFire())) return;

        ItemStack trueThis = (ItemStack)(Object) this;

        if (!(trueThis.getItem() instanceof TieredItem tieredItem)) return;
        if (!(tieredItem.getTier() == MMToolMaterials.INFERNIUM)) return;

        if (randomSource.nextDouble() <= 0.5) {
            cir.setReturnValue(false);
        }
    }

}
