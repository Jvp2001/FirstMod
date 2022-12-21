package firstmod.firstmod.utils.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface WorldGenInfo
{
    int from() default 100;
    int to() default 0;
    int amount() default  8;
}
