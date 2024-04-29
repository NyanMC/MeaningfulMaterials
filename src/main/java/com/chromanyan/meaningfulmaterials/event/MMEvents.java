package com.chromanyan.meaningfulmaterials.event;

import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MMEvents {

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;

        if (itemEntity.getItem().is(MMTags.Items.DEFIES_GRAVITY)) {
            itemEntity.setNoGravity(true);
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(1, 0, 1));
        }
    }

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.isEdible() && itemStack.hasTag()) {
            if (itemStack.getOrCreateTag().getBoolean("infernium_boosted")) {
                event.getToolTip().add(1, Component.translatable("tooltip.meaningfulmaterials.infernium_enhanced"));
            }
        }
    }

    @SubscribeEvent
    public void onUseItem(LivingEntityUseItemEvent.Finish event) {
        ItemStack itemStack = event.getItem();
        if (!itemStack.isEdible() || !itemStack.hasTag()) return;
        if (!itemStack.getOrCreateTag().getBoolean("infernium_boosted")) return;

        LivingEntity livingEntity = event.getEntity();

        livingEntity.hurt(DamageSource.ON_FIRE, 1);
        livingEntity.setSecondsOnFire(4);

        if (livingEntity instanceof Player player) { // in the offchance that a fox gets fed an infernium-boosted steak
            player.getFoodData().eat(4, 0.25f);
        }
    }
}
