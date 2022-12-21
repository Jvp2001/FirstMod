package firstmod.firstmod.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

public class FirstModBlockSettings extends FabricBlockSettings
{



    public enum BlockType
    {
        BuildingBlock,
        Ore,
        Other
    }
    private Item coreIngredient = null;
    private BlockType blockType;
    public static FirstModBlockSettings of(Material material)
    {
        return new FirstModBlockSettings(AbstractBlock.Settings.of(material));
    }
    public FirstModBlockSettings(Block.Settings settings)
    {
        super(settings);
    }

    private Item dropItem = null;
    public FirstModBlockSettings drop(Item item)
    {
        dropItem = item;
        return this;
    }

    public Item getCoreIngredient()
    {
        return coreIngredient;
    }
    public FirstModBlockSettings setCoreIngredient(Item coreIngredient)
    {
        this.coreIngredient = coreIngredient;
        return this;
    }
    public BlockType getBlockType()
    {
        return blockType;
    }
    public FirstModBlockSettings setBlockType(BlockType blockType)
    {
        this.blockType = blockType;
        return this;
    }

    public Item getDropItem()
    {
        return dropItem;
    }
}
