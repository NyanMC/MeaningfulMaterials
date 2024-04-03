package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.content.entity.CosmicArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MeaningfulMaterials.MODID);

    public static final RegistryObject<EntityType<CosmicArrow>> COSMIC_ARROW = ENTITY_TYPES_REGISTRY.register(
            "cosmic_arrow",
            () -> EntityType.Builder.of((EntityType<CosmicArrow> type, Level level) -> new CosmicArrow(type, level), MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("cosmic_arrow")
    );
}
