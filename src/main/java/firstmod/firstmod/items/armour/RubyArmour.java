package firstmod.firstmod.items.armour;

import firstmod.firstmod.utils.annotations.AutoRegister;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

@AutoRegister
public class RubyArmour
{
    private static final Item RUBY_HELMET = new ArmorItem(FirstModArmourMaterials.RUBY, EquipmentSlot.HEAD, new FabricItemSettings());
    private static final Item RUBY_CHESTPLATE = new ArmorItem(FirstModArmourMaterials.RUBY, EquipmentSlot.CHEST, new FabricItemSettings());
    private static final Item RUBY_LEGGINGS = new ArmorItem(FirstModArmourMaterials.RUBY, EquipmentSlot.LEGS, new FabricItemSettings());
    private static final Item RUBY_BOOTS = new ArmorItem(FirstModArmourMaterials.RUBY, EquipmentSlot.FEET, new FabricItemSettings());

}
