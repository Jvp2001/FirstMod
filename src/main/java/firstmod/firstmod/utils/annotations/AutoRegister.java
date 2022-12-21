package firstmod.firstmod.utils.annotations;

import firstmod.firstmod.itemgroups.FirstModItemGroups;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface AutoRegister
{
}
