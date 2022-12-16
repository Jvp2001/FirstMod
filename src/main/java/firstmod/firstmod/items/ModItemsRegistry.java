package firstmod.firstmod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItemsRegistry
{
    public static RubyItem RUBY = new RubyItem(new FabricItemSettings());

    public static void registerItems()
    {
        // Register items here
        Registry.register(Registries.ITEM, new Identifier("firstmod", "ruby"), RUBY);

    }
}
