package firstmod.firstmod.datagen;

import firstmod.firstmod.FirstMod;
import firstmod.firstmod.items.armour.FirstModArmourMaterials;
import firstmod.firstmod.items.tools.FirstModToolMaterials;
import firstmod.firstmod.utils.annotations.BlockDropInfo;
import firstmod.firstmod.utils.annotations.CoreIngredient;
import firstmod.firstmod.utils.Utilities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Consumer;

public class FirstModRecipeProvider extends FabricRecipeProvider
{
    public FirstModRecipeProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter)
    {
        generateItemRecipes(exporter);
        generateBlockRecipes(exporter);
    }

    private void generateBlockRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        Utilities.autoRegister("firstmod.firstmod.blocks", field ->
        {


            var block = (Block) field.get(null);

            if (block == null)
                return;


            var blockDropAnnotationOptional = Utilities.getAnnotationOnClassOrField(BlockDropInfo.class, field);
            var blockDropAnnotation = blockDropAnnotationOptional.orElse(null);
            if (blockDropAnnotation == null)
            {
                throw new RuntimeException("Must have a BlockDropInfo annotation on the field or class of the block");
            }
            var blockType = blockDropAnnotation.blockType();

            Item coreIngredient = getValue(field, Item.class).orElseThrow();
            FirstMod.LOGGER.info("coreIngredient: " + coreIngredient);


            switch (blockType)
            {
                case BuildingBlock ->
                {
                    ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, block)
                            .input('#', coreIngredient)
                            .pattern("###")
                            .pattern("###")
                            .pattern("###")
                            .criterion(RecipeProvider.hasItem(coreIngredient), RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, coreIngredient, 9)
                            .input(block)
                            .criterion(RecipeProvider.hasItem(block), RecipeProvider.conditionsFromItem(block))
                            .offerTo(exporter, new Identifier(FirstMod.MOD_ID, coreIngredient.getName().getString()));
                }

                case Ore ->
                {
                    offerSmelting(exporter, Collections.singletonList(block.asItem()), RecipeCategory.MISC, coreIngredient, 0.7f, 200,"smelting");
                    offerBlasting(exporter, Collections.singletonList(block.asItem()), RecipeCategory.MISC, coreIngredient, 0.7f, 100, "blasting");
                }
            }

        });
    }

    private <T> Optional<T> getValue(Field field, Class<T> type)
    {
        Optional<CoreIngredient> coreIngredientAnnotationOptional = Utilities.getAnnotationOnClassOrField(CoreIngredient.class, field);

        var coreIngredientAnnotation = coreIngredientAnnotationOptional.get();
        return Utilities.getFieldValue(coreIngredientAnnotation.classPath(), coreIngredientAnnotation.ingredientName(), type);

    }

    private void generateItemRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        Utilities.autoRegister("firstmod.firstmod.items", field ->
        {



            field.setAccessible(true);
            Item fieldValue = Utilities.ITEMS.get(field.getName().toLowerCase()).value();


//            if(item instanceof FirstModItem firstModItem)
//            {
//                Field settingsField;
//                try
//                {
//
//                    settingsField = firstModItem.getClass().getField("settings");
//                }
//                catch (NoSuchFieldException e)
//                {
//                    throw new RuntimeException(e);
//                }
//                settingsField.setAccessible(true);
//                coreIngredient = ((FirstModItemSettings) settingsField.get(null)).getCoreIngredient();
//            }


            Item coreIngredient;


            FirstMod.LOGGER.info("Base item: " + fieldValue.getClass().getSimpleName());

            if(fieldValue instanceof ToolItem toolItem)
            {
                var material = toolItem.getMaterial();
                coreIngredient = ((FirstModToolMaterials)material).getCoreIngredient();

                if (field.getType().equals(SwordItem.class))
                {
                    var swordItem = (SwordItem) fieldValue;
                    FirstMod.LOGGER.info("[Sword] Generating recipe for sword item: " + swordItem.getName());

                    ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, toolItem)
                            .input('#', coreIngredient)
                            .input('S', Items.STICK)
                            .pattern(" # ")
                            .pattern(" # ")
                            .pattern(" S ")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                }
                //Do the same above but for shovel
                else if (fieldValue.getClass().isAssignableFrom(ShovelItem.class) && fieldValue instanceof ShovelItem shovelItem)
                {
                    FirstMod.LOGGER.info("[Shovel] Generating recipe for shovel item: ");
                    ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, shovelItem)
                            .input('#', coreIngredient)
                            .input('S', Items.STICK)
                            .pattern(" # ")
                            .pattern(" S ")
                            .pattern(" S ")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                            //.offerTo(exporter, new Identifier(FirstMod.MOD_ID, shovelItem.getName().getString()));
                }
                //Do the same above but for pickaxe
                else if (fieldValue.getClass().isAssignableFrom(PickaxeItem.class) && fieldValue instanceof PickaxeItem pickaxeItem)
                {
                    FirstMod.LOGGER.info("[Pickaxe] Generating recipe for pickaxe item: " + pickaxeItem);
                    ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, pickaxeItem)
                            .input('#', coreIngredient)
                            .input('S', Items.STICK)
                            .pattern("###")
                            .pattern(" S ")
                            .pattern(" S ")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                }
                //Do the same above but for axe
                else if (fieldValue.getClass().isAssignableFrom(AxeItem.class) && fieldValue instanceof AxeItem axeItem)
                {
                    FirstMod.LOGGER.info("[Axe] Generating recipe for axe item: " + axeItem);
                    ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, axeItem)
                            .input('#', coreIngredient)
                            .input('S', Items.STICK)
                            .pattern("## ")
                            .pattern("#S ")
                            .pattern(" S ")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                }
                //Do the same above but for hoe
                else if (fieldValue.getClass().isAssignableFrom(HoeItem.class) && fieldValue instanceof HoeItem hoeItem)
                {
                    FirstMod.LOGGER.info("[Hoe] Generating recipe for hoe item: " + hoeItem);
                    ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, hoeItem)
                            .input('#', coreIngredient)
                            .input('S', Items.STICK)
                            .pattern("## ")
                            .pattern(" S ")
                            .pattern(" S ")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter, new Identifier(FirstMod.MOD_ID, hoeItem.getName().getString()));
                }
            }

            // Do the same as the tools but for armour
            if (fieldValue instanceof ArmorItem armourItem)
            {
                var material = (FirstModArmourMaterials) armourItem.getMaterial();
                coreIngredient = Utilities.ITEMS.get(material.getName()).value();
                FirstMod.LOGGER.info("Generating recipe for armour item: " + armourItem);
                switch (armourItem.getSlotType())
                {

                    case HEAD -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armourItem)
                            .input('#', coreIngredient)
                            .pattern("###")
                            .pattern("# #")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                    case CHEST -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armourItem)
                            .input('#', coreIngredient)
                            .pattern("# #")
                            .pattern("###")
                            .pattern("###")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                    case LEGS -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armourItem)
                            .input('#', coreIngredient)
                            .pattern("###")
                            .pattern("# #")
                            .pattern("# #")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                    case FEET -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armourItem)
                            .input('#', coreIngredient)
                            .pattern("# #")
                            .pattern("# #")
                            .criterion(RecipeProvider.hasItem(coreIngredient),
                                    RecipeProvider.conditionsFromItem(coreIngredient))
                            .offerTo(exporter);
                }
            }

        });
    }
}
