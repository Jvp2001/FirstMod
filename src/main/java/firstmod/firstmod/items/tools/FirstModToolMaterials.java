package firstmod.firstmod.items.tools;

import firstmod.firstmod.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum FirstModToolMaterials implements ToolMaterial
{

    RUBY(1000, 6.0f,
        2.5f, 2, 15, ModItems.RUBY);
    private final int durability;
    private final float miningSpeedMultiplier;
    private final float attackDamage;
    private final int miningLevel;
    private final int enchantability;
    private final Ingredient repairIngredient;

    private final Item coreIngredient;
    FirstModToolMaterials(int durability, float miningSpeedMultiplier, float attackDamage, int miningLevel, int enchantability, Item repairItem)
    {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.miningLevel = miningLevel;
        this.enchantability = enchantability;
        this.repairIngredient = Ingredient.ofItems(repairItem);
        this.coreIngredient = repairItem;
    }

    public Item getCoreIngredient()
    {
        return coreIngredient;
    }

    @Override
    public int getDurability()
    {
        return durability;
    }

    @Override
    public float getMiningSpeedMultiplier()
    {
        return miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage()
    {
        return attackDamage;
    }

    @Override
    public int getMiningLevel()
    {
        return miningLevel;
    }

    @Override
    public int getEnchantability()
    {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return repairIngredient;
    }
}
