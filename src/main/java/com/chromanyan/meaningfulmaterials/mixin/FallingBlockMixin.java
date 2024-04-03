package com.chromanyan.meaningfulmaterials.mixin;

import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlock.class)
public class FallingBlockMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        for(Direction direction : Direction.values()) {
            if (direction == Direction.DOWN) continue; // it won't fall anyway if a block is below

            BlockState otherBlockState = serverLevel.getBlockState(mutableBlockPos.setWithOffset(blockPos, direction));
            if (otherBlockState.is(MMBlocks.COSMITE_BLOCK.get())) {
                ci.cancel();
            }
        }
    }

}
