package com.chromanyan.meaningfulmaterials.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import squeek.appleskin.api.event.FoodValuesEvent;
import squeek.appleskin.api.food.FoodValues;

public class AppleSkinEventHandler {

    @SubscribeEvent
    public void foodValuesEvent(FoodValuesEvent event) {
        ItemStack itemStack = event.itemStack;

        if (!itemStack.hasTag()) return;

        if (itemStack.getOrCreateTag().getBoolean("infernium_boosted")) {
            int hungerTotal = event.modifiedFoodValues.hunger + 4;
            float saturationFlat = event.modifiedFoodValues.saturationModifier * event.modifiedFoodValues.hunger;
            saturationFlat += 1;

            event.modifiedFoodValues = new FoodValues(hungerTotal, saturationFlat / hungerTotal);
        }
    }

}
