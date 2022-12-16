package firstmod.firstmod.blocks;

import firstmod.firstmod.itemgroups.FirstModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class FirstModBlock extends Block
{
    public FirstModBlock(Settings settings)
    {
        super(settings);
        ItemGroupEvents.modifyEntriesEvent(FirstModItemGroups.FIRST_MOD_ITEM_GROUP)
                .register((itemGroup) ->
                        itemGroup.add(new ItemStack(this)));
    }
}
