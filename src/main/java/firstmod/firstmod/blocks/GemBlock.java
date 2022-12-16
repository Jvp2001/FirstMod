package firstmod.firstmod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemBlock extends FirstModBlock
{
    public GemBlock()
    {
        super(FabricBlockSettings.of(Material.METAL).hardness(5.0f).sounds(BlockSoundGroup.METAL).requiresTool());

    }
}
