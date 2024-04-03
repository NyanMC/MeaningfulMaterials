package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.content.entity.CosmicArrow;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
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
    }

}
