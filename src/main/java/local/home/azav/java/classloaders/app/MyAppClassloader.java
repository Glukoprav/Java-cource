package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.api.ApiClassloader;

/**
 * Classloader для класса MyApp
 */
public class MyAppClassloader extends ApiClassloader {
    public MyAppClassloader() {
        super();
        System.out.println("MyAppClassloader после вызова родителя");
    }

    @Override
    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.classloaders.app.MyApp");
    }
}
