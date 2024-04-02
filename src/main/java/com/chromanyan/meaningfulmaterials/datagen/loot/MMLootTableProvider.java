package com.chromanyan.meaningfulmaterials.datagen.loot;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

// thanks tinkers construct
public class MMLootTableProvider extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTables = ImmutableList.of(Pair.of(BlockLootTableProvider::new, LootContextParamSets.BLOCK));

    public MMLootTableProvider(DataGenerator p_124437_) {
        super(p_124437_);
    }

    @Override
    protected @NotNull List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation,LootTable> map, ValidationContext validationtracker) {
        map.forEach((loc, table) -> LootTables.validate(validationtracker, loc, table));
        // Remove vanilla's tables, which we also loaded so we can redirect stuff to them.
        // This ensures the remaining generator logic doesn't write those to files.
        map.keySet().removeIf((loc) -> !loc.getNamespace().equals(MeaningfulMaterials.MODID));
    }

    @Override
    public String getName() {
        return "Meaningful Materials LootTables";
    }
}
