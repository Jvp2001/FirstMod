package firstmod.firstmod.registries;

import firstmod.firstmod.FirstMod;
import firstmod.firstmod.itemgroups.FirstModItemGroups;
import firstmod.firstmod.utils.Utilities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class BlockRegistry
{
    public static <B extends Block> RegistryEntry.Reference<Block> registerBlock(String name, B block)
    {
        return Registry.registerReference(Registries.BLOCK, new Identifier(FirstMod.MOD_ID, name), block);

    }
    public static <B extends Block> RegistryEntry.Reference<Item> registerBlockItem(String name, B block)
    {
        return Registry.registerReference(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlocksInPackage(String packageName)
    {
           Utilities.autoRegister(packageName, field ->
            {
                var block = (Block) field.get(null);
                String name = field.getName().toLowerCase();
                RegistryEntry.Reference<Block> blockReference = registerBlock(field.getName().toLowerCase(), block);
                RegistryEntry.Reference<Item> blockItemReference = registerBlockItem(name, block);
                Utilities.BLOCKS.put(name, blockReference);
                Utilities.BLOCK_ITEMS.put(name, blockItemReference);

                ItemGroupEvents.modifyEntriesEvent(FirstModItemGroups.FIRST_MOD_ITEM_GROUP)
                        .register((itemGroup) ->
                                itemGroup.add(new ItemStack(block)));
            });
    }

}
