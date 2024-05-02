package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.content.entity.CosmicArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class MMDispenserBehaviors {

    public static void registerBehaviors() {
        DispenserBlock.registerBehavior(MMItems.COSMIC_ARROW.get(), new AbstractProjectileDispenseBehavior() {
            protected @NotNull Projectile getProjectile(@NotNull Level level, @NotNull Position position, @NotNull ItemStack itemStack) {
                CosmicArrow cosmicArrow = new CosmicArrow(level, position.x(), position.y(), position.z());
                cosmicArrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return cosmicArrow;
            }
        });

        DispenserBlock.registerBehavior(MMItems.INFERNIUM_LIGHTER.get(), new OptionalDispenseItemBehavior() {
            protected @NotNull ItemStack execute(@NotNull BlockSource pSource, @NotNull ItemStack pStack) {
                Level level = pSource.getLevel();
                this.setSuccess(true);
                Direction direction = pSource.getBlockState().getValue(DispenserBlock.FACING);
                BlockPos blockpos = pSource.getPos().relative(direction);
                BlockState blockstate = level.getBlockState(blockpos);
                if (BaseFireBlock.canBePlacedAt(level, blockpos, direction)) {
                    level.setBlockAndUpdate(blockpos, MMBlocks.INFERNAL_FIRE.get().defaultBlockState());
                    level.gameEvent(null, GameEvent.BLOCK_PLACE, blockpos);
                } else if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
                    if (blockstate.isFlammable(level, blockpos, pSource.getBlockState().getValue(DispenserBlock.FACING).getOpposite())) {
                        blockstate.onCaughtFire(level, blockpos, pSource.getBlockState().getValue(DispenserBlock.FACING).getOpposite(), null);
                        if (blockstate.getBlock() instanceof TntBlock)
                            level.removeBlock(blockpos, false);
                    } else {
                        this.setSuccess(false);
                    }
                } else {
                    level.setBlockAndUpdate(blockpos, blockstate.setValue(BlockStateProperties.LIT, true));
                    level.gameEvent(null, GameEvent.BLOCK_CHANGE, blockpos);
                }

                if (this.isSuccess() && pStack.hurt(1, level.random, null)) {
                    pStack.setCount(0);
                }

                return pStack;
            }
        });
    }

}
