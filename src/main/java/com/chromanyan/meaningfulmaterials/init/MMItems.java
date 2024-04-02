package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class MMItems {
    public static final DeferredRegister<Item> ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MeaningfulMaterials.MODID);

    public static final RegistryObject<Item> COSMITE = ITEMS_REGISTRY.register("cosmite",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
}
