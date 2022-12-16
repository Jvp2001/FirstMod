package firstmod.firstmod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItemsRegistry
{
    public static FirstItem RUBY_GEM = new FirstItem(new FabricItemSettings());

    public static void registerItems()
    {
        // Register items here
        Registry.register(Registries.ITEM, new Identifier("firstmod", "first_item"), RUBY_GEM);

    }
}
