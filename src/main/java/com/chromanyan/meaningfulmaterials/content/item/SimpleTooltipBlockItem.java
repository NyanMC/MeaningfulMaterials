package com.chromanyan.meaningfulmaterials.content.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SimpleTooltipBlockItem extends BlockItem {

    private final String tooltipKey;

    public SimpleTooltipBlockItem(Block p_40565_, Properties p_40566_, String tooltipKey) {
        super(p_40565_, p_40566_);
        this.tooltipKey = tooltipKey;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        list.add(Component.translatable(tooltipKey).withStyle(ChatFormatting.BLUE));
    }
}
