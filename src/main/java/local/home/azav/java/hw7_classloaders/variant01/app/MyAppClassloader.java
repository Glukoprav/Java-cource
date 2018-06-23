package local.home.azav.java.hw7_classloaders.variant01.app;

import local.home.azav.java.hw7_classloaders.variant01.api.ApiClassloader;

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
        return super.loadClass("local.home.azav.java.hw7_classloaders.variant01.app.MyApp");
    }
}
