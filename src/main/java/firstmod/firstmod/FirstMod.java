package firstmod.firstmod;

import firstmod.firstmod.blocks.ModBlocksRegistry;
import firstmod.firstmod.items.ModItemsRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstMod implements ModInitializer
{
    public static final Logger LOGGER = LoggerFactory.getLogger("FirstMod");

    @Override
    public void onInitialize()
    {
        ModItemsRegistry.registerItems();
        ModBlocksRegistry.registerBlocks();

    }
}
