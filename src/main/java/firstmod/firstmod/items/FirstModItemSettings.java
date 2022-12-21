package firstmod.firstmod.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

public class FirstModItemSettings extends FabricItemSettings
{
    private Item coreIngredient = null;


    public FirstModItemSettings setCoreIngredient(Item coreIngredient)
    {
        this.coreIngredient = coreIngredient;
        return this;
    }

    public Item getCoreIngredient()
    {
        return coreIngredient;
    }
}
