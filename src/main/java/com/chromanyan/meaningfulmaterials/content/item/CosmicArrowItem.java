package com.chromanyan.meaningfulmaterials.content.item;

import com.chromanyan.meaningfulmaterials.content.entity.CosmicArrow;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CosmicArrowItem extends ArrowItem {
    public CosmicArrowItem() {
        super(new Properties());
    }

    public @NotNull AbstractArrow createArrow(@NotNull Level p_43237_, @NotNull ItemStack p_43238_, @NotNull LivingEntity p_43239_) {
        return new CosmicArrow(p_43237_, p_43239_);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(Component.translatable("tooltip.meaningfulmaterials.cosmic_arrow").withStyle(ChatFormatting.BLUE));
    }
}
