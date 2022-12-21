package firstmod.firstmod.datagen;

import firstmod.firstmod.FirstMod;
import firstmod.firstmod.utils.Utilities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;

public class FirstModModelProvider extends FabricModelProvider
{
    public FirstModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {

        Utilities.autoRegister("firstmod.firstmod.blocks", field ->
        {
            var block = (Block) field.get(null);
            blockStateModelGenerator.registerCubeAllModelTexturePool(Utilities.BLOCKS.get(field.getName().toLowerCase()).value());

        });
    }




    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        Utilities.autoRegister("firstmod.firstmod.items", field ->
        {

            var item = (Item) field.get(null);
            if(item instanceof ToolItem)
                itemModelGenerator.register(item, Models.HANDHELD);

            else
                itemModelGenerator.register(item,  Models.GENERATED);

        });

        Utilities.autoRegister("firstmod.firstmod.blocks", field ->
        {

            var block = (Block) field.get(null);

            itemModelGenerator.register(Utilities.BLOCK_ITEMS.get(field.getName().toLowerCase()).value(), Models.GENERATED);

        });
    }
}
