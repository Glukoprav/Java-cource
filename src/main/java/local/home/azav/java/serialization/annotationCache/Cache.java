package local.home.azav.java.serialization.annotationCache;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
}

//interface Service {
//@Cache(cacheType = FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, double.class})
//    List<String> run(String item, double value, Date date);
//
//@Cache(cacheType = IN_MEMORY, listList = 100_000)
//    List<String> work(String item);
//        }
