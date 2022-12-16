package firstmod.firstmod.blocks;

import com.google.common.reflect.ClassPath;
import firstmod.firstmod.FirstMod;
import firstmod.firstmod.utils.AutoRegister;
import firstmod.firstmod.utils.BlockRegistry;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class ModBlocksRegistry
{

    public static void registerBlocks()
    {
        FirstMod.LOGGER.info("Registering blocks...");
        // Register blocks here
        var platformClassLoader = ClassLoader.getPlatformClassLoader();
        var blockPackage = platformClassLoader.getDefinedPackage("firstmod.firstmod.blocks");
        try
        {
            ClassPath classPath = ClassPath.from(platformClassLoader);
            classPath.getAllClasses().stream().filter(classInfo -> classInfo.load().getAnnotationsByType(AutoRegister.class).length > 0).forEach(classInfo ->
            {
                try
                {
                    Arrays.stream(classInfo.load().getDeclaredFields()).filter(field ->
                    {
                        var modifiers = field.getModifiers();
                        boolean validModifiers = Modifier.isPublic(modifiers) ||
                                Modifier.isPrivate(modifiers) || Modifier.isProtected(modifiers)
                                && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
                        FirstMod.LOGGER.info("Field: " + field.getName() + " is valid: " + validModifiers);
                        return validModifiers && field.getType().isAssignableFrom(FirstModBlock.class);

                    }).forEach(field ->
                    {
                        try
                        {
                            var block = (FirstModBlock) field.get(null);
                            String blockName = field.getName().toLowerCase();
                            BlockRegistry.registerBlock(blockName, block);
                            BlockRegistry.registerBlockItem(blockName, block);
                            FirstMod.LOGGER.info("Registered block: " + blockName);
                        }
                        catch (IllegalAccessException e)
                        {
                            throw new RuntimeException(e);
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}
