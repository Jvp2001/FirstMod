package firstmod.firstmod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class GemBlock
{
    public static final FirstModBlockSettings DEFAULT_SETTINGS = new FirstModBlockSettings(FabricBlockSettings.of(Material.METAL).strength(5.0f, 4.0f).sounds(BlockSoundGroup.METAL));
    public static FabricBlockSettings CreateBlockSettings(float hardness, float resistance)
    {
        return FabricBlockSettings.of(Material.METAL)
                .hardness(hardness)
                .sounds(BlockSoundGroup.METAL)
                .resistance(resistance)
                .requiresTool();
    }

}
