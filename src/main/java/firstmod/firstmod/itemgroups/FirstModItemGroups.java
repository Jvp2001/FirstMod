package firstmod.firstmod.itemgroups;

import firstmod.firstmod.items.ModItemsRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FirstModItemGroups
{
    public static final ItemGroup FIRST_MOD_ITEM_GROUP = FabricItemGroup.builder(
            new Identifier("firstmod", "first_mod_item_group")).
            icon(() -> new ItemStack(ModItemsRegistry.RUBY_GEM)).build();

    public static void registerItemGroups()
    {
        // Register item groups here

    }
}
