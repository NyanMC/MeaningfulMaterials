package com.chromanyan.meaningfulmaterials.content.entity;

import com.chromanyan.meaningfulmaterials.init.MMEntities;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class CosmicArrow extends AbstractArrow {

    public CosmicArrow(EntityType<? extends CosmicArrow> cosmicArrowType, Level level) {
        super(cosmicArrowType, level);
    }

    public CosmicArrow(Level level, LivingEntity livingEntity) {
        super(MMEntities.COSMIC_ARROW.get(), livingEntity, level);
        this.setNoGravity(true);
    }

    public CosmicArrow(Level level, double x, double y, double z) {
        super(MMEntities.COSMIC_ARROW.get(), x, y, z, level);
        this.setNoGravity(true);
    }

    public void tick() {
        super.tick();

        Vec3 vec3 = this.getDeltaMovement();

        if (this.isInWater()) {
            this.setNoGravity(false);
        }

        if (this.isNoGravity()) {
            this.setDeltaMovement(vec3.scale(1 / 0.99)); // compensate for speed loss
        }
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult p_36755_) {
        super.onHitBlock(p_36755_);
        this.setNoGravity(false);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(MMItems.COSMIC_ARROW.get());
    }
}
