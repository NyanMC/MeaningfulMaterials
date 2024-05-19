package com.chromanyan.meaningfulmaterials.datagen.loot;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataId;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// thanks tinkers construct
public class MMLootTableProvider extends LootTableProvider {

    private final List<SubProviderEntry> lootTables = ImmutableList.of(new SubProviderEntry(BlockLootTableProvider::new, LootContextParamSets.BLOCK));

    public MMLootTableProvider(PackOutput pOutput) {
        super(pOutput, Collections.emptySet(), VanillaLootTableProvider.create(pOutput).getTables());
    }

    @Override
    public @NotNull List<SubProviderEntry> getTables() {
        return lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation,LootTable> map, @NotNull ValidationContext validationtracker) {
        map.forEach((name, loc) -> loc.validate(validationtracker.setParams(loc.getParamSet()).enterElement("{" + name + "}", new LootDataId<>(LootDataType.TABLE, name))));
        // Remove vanilla's tables, which we also loaded so we can redirect stuff to them.
        // This ensures the remaining generator logic doesn't write those to files.
        map.keySet().removeIf((loc) -> !loc.getNamespace().equals(MeaningfulMaterials.MODID));
    }
}
