package firstmod.firstmod.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface AutoRegister
{

}