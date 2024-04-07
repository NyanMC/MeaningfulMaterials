package com.chromanyan.meaningfulmaterials.datagen;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

// thanks silent gems
public class MMWorldGen {
    public static final RuleTest BASE_STONE_END = new TagMatchTest(Tags.Blocks.END_STONES);

    public static void init(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());

        Map<ResourceLocation, ConfiguredFeature<?, ?>> configuredFeatures = Maps.newLinkedHashMap();
        Map<ResourceLocation, PlacedFeature> placedFeatures = Maps.newLinkedHashMap();

        List<Holder<PlacedFeature>> netherFeatureList = Lists.newArrayList();
        List<Holder<PlacedFeature>> endFeatureList = Lists.newArrayList();

        //noinspection OptionalGetWithoutIsPresent
        HolderSet<Biome> netherBiomes = new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_NETHER);
        //noinspection OptionalGetWithoutIsPresent
        HolderSet<Biome> endBiomes = new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_END);

        ResourceLocation cosmiteName = new ResourceLocation(MeaningfulMaterials.MODID, "cosmite_ore");
        ImmutableList<OreConfiguration.TargetBlockState> cosmiteTargetBlocks = ImmutableList.of(
                OreConfiguration.target(BASE_STONE_END, MMBlocks.COSMITE_ORE.get().defaultBlockState())
        );

        ResourceLocation inferniumName = new ResourceLocation(MeaningfulMaterials.MODID, "infernium_ore");
        ImmutableList<OreConfiguration.TargetBlockState> inferniumTargetBlocks = ImmutableList.of(
                OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, MMBlocks.COSMITE_ORE.get().defaultBlockState())
        );

        ConfiguredFeature<?, ?> cosmiteOreFeature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(cosmiteTargetBlocks, 4));
        ConfiguredFeature<?, ?> inferniumOreFeature = new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(inferniumTargetBlocks, 4));

        PlacedFeature cosmiteOrePlaced = new PlacedFeature(
                holder(cosmiteOreFeature, ops, cosmiteName),
                List.of(
                        CountPlacement.of(2),
                        RarityFilter.onAverageOnceEvery(1),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(20), VerticalAnchor.absolute(70)),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        );

        PlacedFeature inferniumOrePlaced = new PlacedFeature(
                holder(inferniumOreFeature, ops, inferniumName),
                List.of(
                        CountPlacement.of(4),
                        RarityFilter.onAverageOnceEvery(1),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(70), VerticalAnchor.absolute(120)),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        );

        configuredFeatures.put(cosmiteName, cosmiteOreFeature);
        placedFeatures.put(cosmiteName, cosmiteOrePlaced);
        endFeatureList.add(holderPlaced(cosmiteOrePlaced, ops, cosmiteName));

        configuredFeatures.put(inferniumName, inferniumOreFeature);
        placedFeatures.put(inferniumName, inferniumOrePlaced);
        netherFeatureList.add(holderPlaced(inferniumOrePlaced, ops, inferniumName));

        BiomeModifier netherOres = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                netherBiomes,
                HolderSet.direct(netherFeatureList),
                GenerationStep.Decoration.UNDERGROUND_ORES
        );

        BiomeModifier endOres = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                endBiomes,
                HolderSet.direct(endFeatureList),
                GenerationStep.Decoration.UNDERGROUND_ORES
        );

        DataProvider configuredFeatureProvider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper,
                MeaningfulMaterials.MODID, ops, Registry.CONFIGURED_FEATURE_REGISTRY, configuredFeatures);
        DataProvider placedFeatureProvider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper,
                MeaningfulMaterials.MODID, ops, Registry.PLACED_FEATURE_REGISTRY, placedFeatures);
        DataProvider biomeModifierProvider = JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper,
                MeaningfulMaterials.MODID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS,
                ImmutableMap.of(
                        new ResourceLocation(MeaningfulMaterials.MODID, "nether_ores"), netherOres,
                        new ResourceLocation(MeaningfulMaterials.MODID, "end_ores"), endOres
                )
        );

        generator.addProvider(true, configuredFeatureProvider);
        generator.addProvider(true, placedFeatureProvider);
        generator.addProvider(true, biomeModifierProvider);
    }

    @SuppressWarnings("unused")
    public static Holder<ConfiguredFeature<?, ?>> holder(ConfiguredFeature<?, ?> feature, RegistryOps<JsonElement> ops, ResourceLocation location) {
        return ops.registryAccess.registryOrThrow(Registry.CONFIGURED_FEATURE_REGISTRY).getOrCreateHolderOrThrow(ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, location));
    }

    @SuppressWarnings("unused")
    public static Holder<PlacedFeature> holderPlaced(PlacedFeature feature, RegistryOps<JsonElement> ops, ResourceLocation location) {
        return ops.registryAccess.registryOrThrow(Registry.PLACED_FEATURE_REGISTRY).getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, location));
    }
}
