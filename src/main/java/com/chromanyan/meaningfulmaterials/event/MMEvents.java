package com.chromanyan.meaningfulmaterials.event;

import com.chromanyan.meaningfulmaterials.init.MMTags;
import com.chromanyan.meaningfulmaterials.init.MMToolMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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

        livingEntity.hurt(livingEntity.damageSources().onFire(), 1);
        livingEntity.setSecondsOnFire(4);

        // in the offchance that a fox gets fed an infernium-boosted steak
        if (!(livingEntity instanceof Player player)) return;

        player.getFoodData().eat(4, 0.25f);
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (!player.isOnFire()) return;

        ItemStack handItem = player.getMainHandItem();
        if (!(handItem.getItem() instanceof TieredItem tieredItem) || tieredItem.getTier() != MMToolMaterials.INFERNIUM) return;

        // ever so slightly faster than netherite but the difference is miniscule so i don't really care
        event.setNewSpeed(event.getNewSpeed() * 1.3f);
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity livingEntity)) return;
        if (!livingEntity.isOnFire()) return;

        ItemStack handItem = livingEntity.getMainHandItem();
        if (!(handItem.getItem() instanceof TieredItem tieredItem) || tieredItem.getTier() != MMToolMaterials.INFERNIUM) return;

        event.setAmount(event.getAmount() + 2);
    }
}
