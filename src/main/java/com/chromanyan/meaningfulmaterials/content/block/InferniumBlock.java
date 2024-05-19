package com.chromanyan.meaningfulmaterials.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class InferniumBlock extends Block {

    public InferniumBlock(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully() && pEntity instanceof LivingEntity livingEntity && !EnchantmentHelper.hasFrostWalker(livingEntity)) {
            pEntity.hurt(pEntity.damageSources().hotFloor(), 1.0F);
        }
    }
}
