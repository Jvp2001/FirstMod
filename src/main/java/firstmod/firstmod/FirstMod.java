package firstmod.firstmod;

import firstmod.firstmod.items.FirstItem;
import firstmod.firstmod.items.ModItemsRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry.*;

public class FirstMod implements ModInitializer
{

    @Override
    public void onInitialize()
    {
        ModItemsRegistry.registerItems();
    }
}
