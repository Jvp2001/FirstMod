package firstmod.firstmod.utils;

import firstmod.firstmod.blocks.FirstModBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockRegistry
{
    public static <B extends FirstModBlock> void registerBlock(String name, B block)
    {
        Registry.register(Registries.BLOCK, name, block);

    }
    public static <B extends FirstModBlock> void registerBlockItem(String name, B block)
    {
        Registry.register(Registries.ITEM, name, new BlockItem(block, new net.minecraft.item.Item.Settings()));
    }

}
