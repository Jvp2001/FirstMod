package firstmod.firstmod.datagen;

import firstmod.firstmod.items.tools.RubyTools;
import firstmod.firstmod.utils.annotations.StoreItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FirstModDataGen implements DataGeneratorEntrypoint
{

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator)
    {
       FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FirstModModelProvider::new);
        pack.addProvider(FirstModRecipeProvider::new);
        pack.addProvider(FirstModLootTables::new);

    }


}
