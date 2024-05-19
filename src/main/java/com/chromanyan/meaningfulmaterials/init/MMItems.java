package com.chromanyan.meaningfulmaterials.init;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.content.item.CosmicArrowItem;
import com.chromanyan.meaningfulmaterials.content.item.CosmiteArmorItem;
import com.chromanyan.meaningfulmaterials.content.item.InferniumLighterItem;
import com.chromanyan.meaningfulmaterials.content.item.SimpleTooltipBlockItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class MMItems {
    public static final DeferredRegister<Item> ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MeaningfulMaterials.MODID);

    public static final RegistryObject<Item> COSMITE = ITEMS_REGISTRY.register("cosmite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFERNIUM_INGOT = ITEMS_REGISTRY.register("infernium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RAW_INFERNIUM = ITEMS_REGISTRY.register("raw_infernium",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> INFERNIUM_DUST = ITEMS_REGISTRY.register("infernium_dust",
            () -> new Item(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> COSMIC_ARROW = ITEMS_REGISTRY.register("cosmic_arrow", CosmicArrowItem::new);

    public static final RegistryObject<Item> COSMITE_BOOTS = ITEMS_REGISTRY.register("cosmite_boots",
            () -> new CosmiteArmorItem(EquipmentSlot.FEET, new Item.Properties()));

    public static final RegistryObject<Item> INFERNIUM_LIGHTER = ITEMS_REGISTRY.register("infernium_lighter", InferniumLighterItem::new);

    public static final RegistryObject<Item> INFERNIUM_SWORD = ITEMS_REGISTRY.register("infernium_sword",
            () -> new SwordItem(MMToolMaterials.INFERNIUM, 3, -2.4f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> INFERNIUM_SHOVEL = ITEMS_REGISTRY.register("infernium_shovel",
            () -> new ShovelItem(MMToolMaterials.INFERNIUM, 1.5f, -3.0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> INFERNIUM_PICKAXE = ITEMS_REGISTRY.register("infernium_pickaxe",
            () -> new PickaxeItem(MMToolMaterials.INFERNIUM, 1, -2.8f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> INFERNIUM_AXE = ITEMS_REGISTRY.register("infernium_axe",
            () -> new AxeItem(MMToolMaterials.INFERNIUM, 6.0f, -3.0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> INFERNIUM_HOE = ITEMS_REGISTRY.register("infernium_hoe",
            () -> new HoeItem(MMToolMaterials.INFERNIUM, -2, 0.0f,
                    new Item.Properties().fireResistant()));

    public static final RegistryObject<BlockItem> COSMITE_BLOCK_ITEM = ITEMS_REGISTRY.register("cosmite_block",
            () -> new BlockItem(MMBlocks.COSMITE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> COSMITE_ORE_ITEM = ITEMS_REGISTRY.register("cosmite_ore",
            () -> new BlockItem(MMBlocks.COSMITE_ORE.get(), new Item.Properties()));

    public static final RegistryObject<BlockItem> INFERNIUM_BLOCK_ITEM = ITEMS_REGISTRY.register("infernium_block",
            () -> new BlockItem(MMBlocks.INFERNIUM_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<BlockItem> INFERNIUM_ORE_ITEM = ITEMS_REGISTRY.register("infernium_ore",
            () -> new BlockItem(MMBlocks.INFERNIUM_ORE.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<BlockItem> RAW_INFERNIUM_BLOCK_ITEM = ITEMS_REGISTRY.register("raw_infernium_block",
            () -> new BlockItem(MMBlocks.RAW_INFERNIUM_BLOCK.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<BlockItem> COSMIC_LANTERN_ITEM = ITEMS_REGISTRY.register("cosmic_lantern",
            () -> new SimpleTooltipBlockItem(MMBlocks.COSMIC_LANTERN.get(), new Item.Properties(), "tooltip.meaningfulmaterials.cosmic_lantern"));
}
