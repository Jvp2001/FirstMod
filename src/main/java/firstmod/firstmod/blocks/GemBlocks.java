package firstmod.firstmod.blocks;

import firstmod.firstmod.items.ModItems;
import firstmod.firstmod.utils.annotations.AutoRegister;
import firstmod.firstmod.utils.annotations.BlockDropInfo;
import firstmod.firstmod.utils.annotations.CoreIngredient;
import firstmod.firstmod.utils.annotations.WorldGenInfo;
import net.minecraft.block.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;

@AutoRegister

public final class GemBlocks
{

    @CoreIngredient(classPath = "firstmod.firstmod.items.ModItems", ingredientName = "RUBY")
    @BlockDropInfo(
            blockType = FirstModBlockSettings.BlockType.BuildingBlock,
            dropItemClassPath = "firstmod.firstmod.items.ModItems",
            dropItemFieldName = "RUBY"
    )
    public static final Block RUBY_BLOCK = new Block(GemBlock.DEFAULT_SETTINGS.setCoreIngredient(ModItems.RUBY).setBlockType(FirstModBlockSettings.BlockType.BuildingBlock));


    @WorldGenInfo()
    @CoreIngredient(classPath = "firstmod.firstmod.items.ModItems", ingredientName = "RUBY")
    @BlockDropInfo(
            blockType = FirstModBlockSettings.BlockType.Ore,
            dropsSelf = true
    )
    public static final Block RUBY_ORE = new ExperienceDroppingBlock((GemBlock.DEFAULT_SETTINGS.setBlockType(FirstModBlockSettings.BlockType.Ore).setCoreIngredient(ModItems.RUBY)).drop(ModItems.RUBY), UniformIntProvider.create(3,7));


}
