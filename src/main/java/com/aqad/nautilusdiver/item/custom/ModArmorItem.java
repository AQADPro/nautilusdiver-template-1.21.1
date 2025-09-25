package com.aqad.nautilusdiver.item.custom;

import com.aqad.nautilusdiver.item.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(MobEffects.JUMP, 200, 1, false, false, true),
                                    new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, false, true)))
                    .build();

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        if (!level.isClientSide && entity instanceof Player player) {
            if (this.getType() == Type.HELMET && slot == 39) {
                List<MobEffectInstance> effects = MATERIAL_TO_EFFECT_MAP.get(this.getMaterial());
                if (effects != null) {
                    for (MobEffectInstance effect : effects) {
                        player.addEffect(new MobEffectInstance(
                                effect.getEffect(),
                                210,
                                effect.getAmplifier(),
                                effect.isAmbient(),
                                effect.isVisible(),
                                effect.showIcon()
                        ));
                    }
                }
            }
        }
    }
}
