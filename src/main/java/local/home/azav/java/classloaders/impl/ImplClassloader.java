package local.home.azav.java.classloaders.impl;

import local.home.azav.java.classloaders.api.ApiClassloader;

import java.net.URL;

/**
 * Classloader для класса CalculatorImpl
 */
public class ImplClassloader extends ApiClassloader {

    public ImplClassloader() {
        super();
        System.out.println("ImplClassloader после вызова родителя");
    }
}
