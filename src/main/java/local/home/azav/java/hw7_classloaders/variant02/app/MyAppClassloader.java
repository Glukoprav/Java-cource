package local.home.azav.java.hw7_classloaders.variant02.app;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;

/**
 * Classloader для класса MyApp
 */
public class MyAppClassloader extends ApiClassloader {
    public final String classPathMy;   // путь к классу

    public MyAppClassloader(String classPathMy) {
        super(classPathMy);
        this.classPathMy = classPathMy;
        System.out.println("MyAppClassloader после вызова родителя");
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
