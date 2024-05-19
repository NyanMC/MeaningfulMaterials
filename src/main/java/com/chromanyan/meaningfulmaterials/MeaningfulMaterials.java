package com.chromanyan.meaningfulmaterials;

import com.chromanyan.meaningfulmaterials.datagen.MMBlockStates;
import com.chromanyan.meaningfulmaterials.datagen.MMItemModels;
import com.chromanyan.meaningfulmaterials.datagen.MMRecipes;
import com.chromanyan.meaningfulmaterials.datagen.MMWorldGen;
import com.chromanyan.meaningfulmaterials.datagen.loot.MMLootTableProvider;
import com.chromanyan.meaningfulmaterials.datagen.tags.MMBlockTags;
import com.chromanyan.meaningfulmaterials.datagen.tags.MMItemTags;
import com.chromanyan.meaningfulmaterials.event.MMEvents;
import com.chromanyan.meaningfulmaterials.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MeaningfulMaterials.MODID)
public class MeaningfulMaterials {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "meaningfulmaterials";
    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();

    public MeaningfulMaterials() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MMEntities.ENTITY_TYPES_REGISTRY.register(modEventBus);
        MMBlocks.BLOCKS_REGISTRY.register(modEventBus);
        MMItems.ITEMS_REGISTRY.register(modEventBus);
        MMRecipeSerializers.RECIPE_SERIALIZERS_REGISTRY.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::addCreative);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new MMEvents());
        MMDispenserBehaviors.registerBehaviors();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(MMItems.COSMITE.get());
            event.accept(MMItems.INFERNIUM_INGOT.get());
            event.accept(MMItems.RAW_INFERNIUM.get());
            event.accept(MMItems.INFERNIUM_DUST.get());
        }

        if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            event.accept(MMItems.COSMITE_BOOTS.get());
            event.accept(MMItems.INFERNIUM_SWORD.get());
        }

        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(MMItems.INFERNIUM_SHOVEL.get());
            event.accept(MMItems.INFERNIUM_PICKAXE.get());
            event.accept(MMItems.INFERNIUM_AXE.get());
            event.accept(MMItems.INFERNIUM_HOE.get());
        }

        if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
            event.accept(MMItems.COSMITE_BLOCK_ITEM.get());
            event.accept(MMItems.INFERNIUM_BLOCK_ITEM.get());
            event.accept(MMItems.COSMIC_LANTERN_ITEM.get());
        }

        if (event.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            event.accept(MMItems.COSMITE_ORE_ITEM.get());
            event.accept(MMItems.INFERNIUM_ORE_ITEM.get());
            event.accept(MMItems.RAW_INFERNIUM_BLOCK_ITEM.get());
        }
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();

        MMBlockTags blockTags = new MMBlockTags(gen, efh);
        if (event.includeServer()) {
            gen.addProvider(true, blockTags);
            gen.addProvider(true, new MMItemTags(gen, blockTags, efh));

            gen.addProvider(true, new MMLootTableProvider(gen));

            gen.addProvider(true, new MMRecipes(gen));

            MMWorldGen.init(gen, efh);
        }

        if (event.includeClient()) {
            gen.addProvider(true, new MMBlockStates(gen, efh));
            gen.addProvider(true, new MMItemModels(gen, efh));
        }
    }
}
