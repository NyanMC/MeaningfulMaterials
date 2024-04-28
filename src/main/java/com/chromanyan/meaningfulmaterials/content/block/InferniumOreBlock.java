package com.chromanyan.meaningfulmaterials.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InferniumOreBlock extends Block {

    public static final BooleanProperty PLACES_LAVA = BooleanProperty.create("places_lava");

    public InferniumOreBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .strength(4.5F, 6.0F));
        this.registerDefaultState(this.getStateDefinition().any().setValue(PLACES_LAVA, true));
    }

    @Override
    public void playerDestroy(@NotNull Level pLevel, @NotNull Player pPlayer, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable BlockEntity pBlockEntity, @NotNull ItemStack pTool) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);

        if (pState.getValue(PLACES_LAVA) && pLevel.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && pTool.getEnchantmentLevel(Enchantments.SILK_TOUCH) == 0) {
            pLevel.setBlock(pPos, Blocks.LAVA.defaultBlockState(), pLevel.isClientSide ? 11 : 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> p_49915_) {
        p_49915_.add(PLACES_LAVA);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext p_49820_) {
        return this.defaultBlockState().setValue(PLACES_LAVA, false);
    }
}
