package firstmod.firstmod.items;

import firstmod.firstmod.utils.annotations.AutoRegister;
import firstmod.firstmod.utils.annotations.CoreIngredient;
import firstmod.firstmod.utils.annotations.StoreItems;
import net.minecraft.item.Item;

@AutoRegister
@StoreItems
public final class ModItems
{
    @CoreIngredient(classPath = "firstmod.firstmod.items.ModItems", ingredientName = "RUBY")
    public static Item RUBY = new Item(new FirstModItemSettings());
    @CoreIngredient(classPath = "firstmod.firstmod.items.ModItems", ingredientName = "RUBY_DUST")
    public static Item RUBY_DUST = new Item(new FirstModItemSettings());

}
