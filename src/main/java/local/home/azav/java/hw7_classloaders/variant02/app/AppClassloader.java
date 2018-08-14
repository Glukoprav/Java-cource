package local.home.azav.java.hw7_classloaders.variant02.app;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;

/**
 * Classloader для класса AppCalc
 */
public class AppClassloader extends ApiClassloader {
    public final String classPathApp;   // путь к классу

    public AppClassloader(String classPathApp) {
        super(classPathApp);
        this.classPathApp = classPathApp;
        System.out.println("AppClassloader после вызова родителя");
    }

    @Override
    public Class loadClass(String name,boolean resolve) throws ClassNotFoundException
    {
        Class result= findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }
}
