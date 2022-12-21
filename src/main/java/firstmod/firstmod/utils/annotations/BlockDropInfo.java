package firstmod.firstmod.utils.annotations;

import firstmod.firstmod.blocks.FirstModBlockSettings;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface BlockDropInfo
{
    FirstModBlockSettings.BlockType blockType();
    String dropItemClassPath() default "";
    String dropItemFieldName() default "";
    boolean dropsSelf() default false;
    int dropAmount() default 1;
    int dropChance() default 100;
}
