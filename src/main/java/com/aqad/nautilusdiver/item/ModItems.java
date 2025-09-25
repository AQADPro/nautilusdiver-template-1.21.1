package com.aqad.nautilusdiver.item;

import com.aqad.nautilusdiver.NautilusDiver;
import com.aqad.nautilusdiver.item.custom.ModArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NautilusDiver.MOD_ID);

    public static final DeferredItem<ArmorItem> NAUTILUS_HELMET = ITEMS.register("nautilus_helmet",
            () -> new ModArmorItem(ModArmorMaterials.NAUTILUS_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(25))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}