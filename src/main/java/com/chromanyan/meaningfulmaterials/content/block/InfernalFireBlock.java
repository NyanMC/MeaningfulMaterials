package com.chromanyan.meaningfulmaterials.content.block;

import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class InfernalFireBlock extends BaseFireBlock {

    public InfernalFireBlock() {
        super(Properties.of(Material.FIRE, MaterialColor.FIRE).noCollission().instabreak().lightLevel(value -> 15).sound(SoundType.WOOL), 1f);
    }

    @Override
    public void entityInside(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Entity pEntity) {
        super.entityInside(pState, pLevel, pPos, pEntity);

        if (!pEntity.fireImmune()) {
            pEntity.setSecondsOnFire(120);
        }
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext pContext) {
        return MMBlocks.INFERNAL_FIRE.get().defaultBlockState();
    }

    public @NotNull BlockState updateShape(@NotNull BlockState pState, @NotNull Direction pFacing, @NotNull BlockState pFacingState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pFacingPos) {
        return this.canSurvive(pState, pLevel, pCurrentPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    public boolean canSurvive(@NotNull BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        return pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, Direction.UP);
    }

    @Override
    protected boolean canBurn(@NotNull BlockState pState) {
        return true;
    }
}
