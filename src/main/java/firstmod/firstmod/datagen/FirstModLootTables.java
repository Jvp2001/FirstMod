package firstmod.firstmod.datagen;

import firstmod.firstmod.utils.annotations.BlockDropInfo;
import firstmod.firstmod.utils.Utilities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class FirstModLootTables extends FabricBlockLootTableProvider
{

    public FirstModLootTables(FabricDataOutput dataOutput)
    {
        super(dataOutput);
    }

    @Override
    public void generate()
    {
        Utilities.autoRegister("firstmod.firstmod.blocks", field ->
        {
            var block = Utilities.BLOCKS.get(field.getName().toLowerCase()).value();

            var blockDropAnnotationOptional = Utilities.getAnnotationOnClassOrField(BlockDropInfo.class, field);
            BlockDropInfo blockDropAnnotation = blockDropAnnotationOptional.orElseThrow();
            var fieldValueOptional = Utilities.getFieldValue(blockDropAnnotation.dropItemClassPath(), blockDropAnnotation.dropItemFieldName(), Block.class);


            Item item = Utilities.ITEMS.get(blockDropAnnotation.dropItemFieldName().toLowerCase()).value();
            Item dropItem = blockDropAnnotation.dropsSelf() ? block.asItem() : item;

            switch (blockDropAnnotation.blockType())
            {

                case BuildingBlock, Other -> super.addDrop(block);
                case Ore -> super.oreDrops(block, dropItem);
            }

        });
    }
}

