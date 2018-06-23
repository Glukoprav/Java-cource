package local.home.azav.java.hw9_serialization.annotationCache;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотаця, включающая кэширование результатов вызова метода
 * у реализации какого-либо класса
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    /**
     * Выбор пространства для кэширования
     * согласно перечислению CacheInSpace:
     * в память, или в файл.
     */
    CacheInSpace value() default CacheInSpace.MEMORY;

    /**
     * Путь к кэширующим файлам
     */
    String pathFile() default "c:/temp/";

    /**
     * Префикс для кэширующих файлов
     */
    String fileNamePrefix() default "data";

    /**
     * Расширение для кэширующих файлов
     */
    String fileExtension() default ".ssk";
}
