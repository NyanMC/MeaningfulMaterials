package com.chromanyan.meaningfulmaterials.content.item;

import com.chromanyan.meaningfulmaterials.MeaningfulMaterials;
import com.chromanyan.meaningfulmaterials.init.MMArmorMaterials;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.UUID;

public class CosmiteArmorItem extends ArmorItem {
    private static final EnumMap<Type, UUID> ARMOR_MODIFIER_UUID_PER_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266744_) -> {
        p_266744_.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        p_266744_.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        p_266744_.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        p_266744_.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    private final Multimap<Attribute, AttributeModifier> trueDefaultModifiers;

    public CosmiteArmorItem(ArmorItem.Type armorType, Properties p_40388_) {
        super(MMArmorMaterials.COSMITE, armorType, p_40388_);

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(armorType);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.getToughness(), AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        builder.put(ForgeMod.ENTITY_GRAVITY.get(), new AttributeModifier(uuid, "Entity gravity", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));

        trueDefaultModifiers = builder.build();
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot p_40390_) {
        return p_40390_ == this.type.getSlot() ? this.trueDefaultModifiers : super.getDefaultAttributeModifiers(p_40390_);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return String.format("%s:textures/models/armor/%s_layer_%s.png", MeaningfulMaterials.MODID, this.getMaterial().getName(), slot == EquipmentSlot.LEGS ? 2 : 1);
    }
}
