package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.client.renderer.CosmicArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MMRenderers {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MMEntities.COSMIC_ARROW.get(), CosmicArrowRenderer::new);
    }
}
