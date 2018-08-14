package local.home.azav.java.hw7_classloaders.variant02.impl;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;


/**
 * Classloader для класса CalculatorImpl
 */
public class ImplClassloader extends ApiClassloader {
    public final String classPathImpl;   // путь к классу

    public ImplClassloader(String classPathImpl) {
        super(classPathImpl);
        this.classPathImpl = classPathImpl;
        System.out.println("ImplClassloader после вызова родителя");
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
