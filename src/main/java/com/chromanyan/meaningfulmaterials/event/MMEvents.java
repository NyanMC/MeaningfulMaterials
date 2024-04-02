package com.chromanyan.meaningfulmaterials.event;

import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MMEvents {

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;

        if (itemEntity.getItem().is(MMTags.Items.DEFIES_GRAVITY)) {
            itemEntity.setNoGravity(true);
        }
    }
}
