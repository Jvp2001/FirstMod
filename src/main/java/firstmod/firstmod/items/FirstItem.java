package firstmod.firstmod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FirstItem extends FirstModItem
{
    public FirstItem(Settings settings)
    {
        super(settings.fireproof().maxCount(16));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        if (!world.isClient)
        {
            user.sendMessage(Text.of("Hello, world!"), true);
        }
        return super.use(world, user, hand);
    }
}
