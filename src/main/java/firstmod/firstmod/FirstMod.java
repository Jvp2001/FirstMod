package firstmod.firstmod;

import firstmod.firstmod.registries.BlockRegistry;
import firstmod.firstmod.registries.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstMod implements ModInitializer
{
    public static final String MOD_ID = "firstmod";
    public static final Logger LOGGER = LoggerFactory.getLogger("FirstMod");



    public static final RegistryKey<PlacedFeature> RUBY_ORE_PLACED_FEATURE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID, "ruby_ore"));


    @Override
    public void onInitialize()
    {

        BiomeModifications.create(new Identifier(MOD_ID, "features")).add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(),
                (biomeSelectionContext, biomeModificationContext) ->
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, RUBY_ORE_PLACED_FEATURE));

        BlockRegistry.registerBlocksInPackage("firstmod.firstmod.blocks");
        ItemRegistry.registerItemsInPackage("firstmod.firstmod.items");


    }
}
