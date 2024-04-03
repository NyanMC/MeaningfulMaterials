package com.chromanyan.meaningfulmaterials.client.renderer;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.content.entity.CosmicArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CosmicArrowRenderer extends ArrowRenderer<CosmicArrow> {

    public CosmicArrowRenderer(EntityRendererProvider.Context p_173917_) {
        super(p_173917_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CosmicArrow p_114482_) {
        return new ResourceLocation(MeaningfulMaterials.MODID, "textures/entity/projectiles/cosmic_arrow.png");
    }
}
