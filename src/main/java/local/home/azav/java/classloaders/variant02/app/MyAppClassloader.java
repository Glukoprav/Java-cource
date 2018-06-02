package local.home.azav.java.classloaders.variant02.app;

import local.home.azav.java.classloaders.variant02.api.ApiClassloader;

/**
 * Classloader для класса MyApp
 */
public class MyAppClassloader extends ApiClassloader {
    public final String classPath;   // путь к классу

    public MyAppClassloader(String classPath) {
        super(classPath);
        //super();
        this.classPath = classPath;
        System.out.println("MyAppClassloader после вызова родителя");
    }

//    @Override
//    public Class<?> loadClass() throws ClassNotFoundException {
//        return super.loadClass("local.home.azav.java.classloaders.variant02.app.MyApp");
//    }
}
