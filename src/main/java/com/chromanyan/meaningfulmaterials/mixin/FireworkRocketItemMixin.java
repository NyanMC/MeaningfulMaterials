package com.chromanyan.meaningfulmaterials.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketItemMixin {

    @Inject(method = "appendHoverText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", shift = At.Shift.AFTER))
    private void appendHoverText(ItemStack p_41211_, Level p_41212_, List<Component> p_41213_, TooltipFlag p_41214_, CallbackInfo ci) {
        CompoundTag compoundtag = p_41211_.getTagElement("Fireworks");
        if (compoundtag == null) return;

        if (compoundtag.getBoolean("Cosmite")) {
            p_41213_.add(Component.translatable("tooltip.meaningfulmaterials.cosmite_boosted"));
        }
    }

}
