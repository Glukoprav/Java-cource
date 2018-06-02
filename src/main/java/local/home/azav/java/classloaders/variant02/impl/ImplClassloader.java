package local.home.azav.java.classloaders.variant02.impl;

import local.home.azav.java.classloaders.variant02.api.ApiClassloader;


/**
 * Classloader для класса CalculatorImpl
 */
public class ImplClassloader extends ApiClassloader {
    public final String classPath;   // путь к классу

    public ImplClassloader(String classPath) {
        super(classPath);
        this.classPath = classPath;
        System.out.println("ImplClassloader после вызова родителя");
    }
}
