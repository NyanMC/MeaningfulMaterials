package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.content.item.CosmicArrowItem;
import com.chromanyan.meaningfulmaterials.content.item.CosmiteArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
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

    public static final RegistryObject<Item> COSMIC_ARROW = ITEMS_REGISTRY.register("cosmic_arrow", CosmicArrowItem::new);

    public static final RegistryObject<Item> COSMITE_BOOTS = ITEMS_REGISTRY.register("cosmite_boots",
            () -> new CosmiteArmorItem(EquipmentSlot.FEET, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<BlockItem> COSMITE_BLOCK_ITEM = ITEMS_REGISTRY.register("cosmite_block",
            () -> new BlockItem(MMBlocks.COSMITE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> COSMITE_ORE_ITEM = ITEMS_REGISTRY.register("cosmite_ore",
            () -> new BlockItem(MMBlocks.COSMITE_ORE.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<BlockItem> COSMIC_LANTERN_ITEM = ITEMS_REGISTRY.register("cosmic_lantern",
            () -> new BlockItem(MMBlocks.COSMIC_LANTERN.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
}
