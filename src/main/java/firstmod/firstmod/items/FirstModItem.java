package firstmod.firstmod.items;

import net.minecraft.item.Item;

public class FirstModItem extends Item
{
    private final Settings settings;

    public FirstModItem(Settings settings)
    {
        super(settings);
        this.settings = settings;
    }
}
