package firstmod.firstmod.utils;

import firstmod.firstmod.FirstMod;
import firstmod.firstmod.utils.annotations.AutoRegister;
import firstmod.firstmod.utils.annotations.DoNotRegister;
import firstmod.firstmod.utils.annotations.StoreItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Utilities
{


    @FunctionalInterface
    public interface FieldConsumer
    {
        void accept(Field field) throws IllegalAccessException;
    }

    public static <T> Optional<T>  getFieldValue(String packageName, String fieldName, Class<T> type)
    {
        try
        {
            return Optional.ofNullable(type.cast(Class.forName(packageName).getField(fieldName).get(null)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public static <A extends Annotation> Optional<A> getAnnotationOnClassOrField(Class<A> annotationClass, Field field)
    {
        A annotation = field.getAnnotation(annotationClass);
        if(annotation == null)
            annotation = field.getDeclaringClass().getAnnotation(annotationClass);
        return Optional.ofNullable(annotation);
    }
    public static void autoRegister(String packageName, FieldConsumer fieldConsumer)
    {
        Set<Class<?>> typesAnnotatedWith = new Reflections(packageName)
                .getTypesAnnotatedWith(AutoRegister.class);

        for (Class<?> aClass : typesAnnotatedWith)
        {
            if(aClass.getAnnotation(DoNotRegister.class) != null)
                continue;
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields)
            {
                declaredField.setAccessible(true);
                if (((declaredField.getModifiers() & Modifier.STATIC) != 0) && declaredField.getAnnotation(DoNotRegister.class) == null)
                {
                    try
                    {
                        fieldConsumer.accept(declaredField);
                    }
                    catch (IllegalAccessException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }

    public static void storeItems(String packagePath)
    {
        Set<Class<?>> typesAnnotatedWith = new Reflections(packagePath)
                .getTypesAnnotatedWith(StoreItems.class);
        for (Class<?> aClass : typesAnnotatedWith)
        {
            storeItems(aClass);
        }

    }
    public static void storeItems(Class<?> clazz)
    {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields)
        {
            declaredField.setAccessible(true);
            if (((declaredField.getModifiers() & Modifier.STATIC) != 0) && declaredField.getAnnotation(DoNotRegister.class) == null && clazz.getAnnotation(StoreItems.class) != null)
            {
                try
                {
                    if(declaredField.getType().asSubclass(Item.class) != null)

                    {
                        Item item = (Item)declaredField.get(null);
                        if(item != null)
                        {
                            String name = declaredField.getName().toLowerCase();
                            ITEMS.put(name, Registry.registerReference(Registries.ITEM, new Identifier(FirstMod.MOD_ID, name), (Item) declaredField.get(null)));

                        }
                    }
                }
                catch (IllegalAccessException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static final Map<String, RegistryEntry.Reference<Item>> ITEMS = new HashMap<>();
    public static final Map<String, RegistryEntry.Reference<Block>> BLOCKS = new HashMap<>();
    public static final Map<String, RegistryEntry.Reference<Item>> BLOCK_ITEMS = new HashMap<>();

}
