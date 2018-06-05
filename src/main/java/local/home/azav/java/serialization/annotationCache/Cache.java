package local.home.azav.java.serialization.annotationCache;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    CacheInSpace value() default CacheInSpace.MEMORY;
    String pathFile() default "c:/temp/serialized.tss";
}
