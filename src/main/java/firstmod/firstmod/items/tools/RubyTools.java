package firstmod.firstmod.items.tools;

import firstmod.firstmod.items.FirstModItemSettings;
import firstmod.firstmod.items.ModItems;
import firstmod.firstmod.utils.annotations.AutoRegister;
import firstmod.firstmod.utils.annotations.CoreIngredient;
import firstmod.firstmod.utils.annotations.DoNotRegister;
import firstmod.firstmod.utils.annotations.StoreItems;
import net.minecraft.item.*;

@AutoRegister
@StoreItems
@CoreIngredient(classPath = "firstmod.firstmod.items.ModItems", ingredientName = "RUBY")
public final class RubyTools
{

    @DoNotRegister
    public static final FirstModItemSettings RUBY_TOOL_SETTINGS = new FirstModItemSettings()
            .setCoreIngredient(ModItems.RUBY);

    private static final SwordItem RUBY_SWORD =
            new SwordItem(FirstModToolMaterials.RUBY,-3, -2.4f,
                    RUBY_TOOL_SETTINGS);

    private static final PickaxeItem RUBY_PICKAXE =
            new PickaxeItem(FirstModToolMaterials.RUBY, 1, -2.8f,
                    RUBY_TOOL_SETTINGS);

    public static final ShovelItem RUBY_SHOVEL =
            new ShovelItem(FirstModToolMaterials.RUBY, 1.5f, -3.0f,
                    RUBY_TOOL_SETTINGS);
    public static final HoeItem RUBY_HOE =
            new FirstModHoeTool(FirstModToolMaterials.RUBY, -3, -1.0f,
                    RUBY_TOOL_SETTINGS);
}
