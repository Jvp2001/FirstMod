package firstmod.firstmod.items;

import firstmod.firstmod.itemgroups.FirstModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class FirstModItem extends Item
{
    public FirstModItem(Settings settings)
    {
        super(settings);
        ItemGroupEvents.modifyEntriesEvent(FirstModItemGroups.FIRST_MOD_ITEM_GROUP)
                .register((itemGroup) ->
                itemGroup.add(new ItemStack(this)));

    }

}
