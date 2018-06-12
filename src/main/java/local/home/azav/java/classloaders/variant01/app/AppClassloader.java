package local.home.azav.java.classloaders.variant01.app;

import local.home.azav.java.classloaders.variant01.api.ApiClassloader;

/**
 * Classloader для класса AppCalc
 */
public class AppClassloader extends ApiClassloader {
    public AppClassloader() {
        super();
        System.out.println("AppClassloader после вызова родителя");
    }

    @Override
    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.classloaders.variant01.app.AppCalc");
    }


}