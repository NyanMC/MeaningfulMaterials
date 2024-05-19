package com.chromanyan.meaningfulmaterials.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CosmicLanternBlock extends LanternBlock {
    public CosmicLanternBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops()
                .strength(3.5F).sound(SoundType.LANTERN).lightLevel((p_187433_) -> 15).noOcclusion());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_153467_) {
        FluidState fluidstate = p_153467_.getLevel().getFluidState(p_153467_.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(HANGING, false);

        return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState p_153479_, @NotNull LevelReader p_153480_, @NotNull BlockPos p_153481_) {
        return true;
    }
}
