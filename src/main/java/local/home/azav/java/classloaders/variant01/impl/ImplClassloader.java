package local.home.azav.java.classloaders.variant01.impl;

import local.home.azav.java.classloaders.variant01.api.ApiClassloader;

/**
 * Classloader для класса CalculatorImpl
 */
public class ImplClassloader extends ApiClassloader {

    public ImplClassloader() {
        super();
        System.out.println("ImplClassloader после вызова родителя");
    }

    @Override
    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.classloaders.variant01.impl.CalculatorImpl");
    }
}
