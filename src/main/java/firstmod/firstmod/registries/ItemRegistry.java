package firstmod.firstmod.registries;

import firstmod.firstmod.FirstMod;
import firstmod.firstmod.itemgroups.FirstModItemGroups;
import firstmod.firstmod.utils.Utilities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ItemRegistry
{
    public static <I extends Item> RegistryEntry.Reference<Item> registerItem(String name, I item)
    {

        return Registry.registerReference(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), item);
    }

    public static void registerItemsInPackage(String packageName)
    {
        Utilities.autoRegister(packageName, field ->
        {

            Item item;
            try
            {
                if ((item = (Item) field.get(null)) != null)
                {
                    String name = field.getName().toLowerCase();
                    var reference = registerItem(name, item);

                    Utilities.ITEMS.put(name, reference);
                    ItemGroupEvents.modifyEntriesEvent(FirstModItemGroups.FIRST_MOD_ITEM_GROUP)
                            .register((itemGroup) ->
                                    itemGroup.add(new ItemStack(item)));
                }
            }
            catch (IllegalAccessException e)
            {
                throw new RuntimeException(e);
            }
        });

    }
}
