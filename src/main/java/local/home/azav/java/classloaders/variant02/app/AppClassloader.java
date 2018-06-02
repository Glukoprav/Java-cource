package local.home.azav.java.classloaders.variant02.app;

import local.home.azav.java.classloaders.variant02.api.ApiClassloader;

/**
 * Classloader для класса AppCalc
 */
public class AppClassloader extends ApiClassloader {
    public final String classPath;   // путь к классу

    public AppClassloader(String classPath) {
        super(classPath);
        //super();
        this.classPath = classPath;
        System.out.println("AppClassloader после вызова родителя");
    }
}
