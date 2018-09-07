package local.home.azav.java.hw7_classloaders.variant02.impl;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classloader для класса CalculatorImpl
 */
public class ImplClassloader extends ApiClassloader {
    private static final Logger LOG = Logger.getLogger(ImplClassloader.class.getName());

    public ImplClassloader(String classPathImpl) {
        super(classPathImpl);
        LOG.log(Level.INFO,"ImplClassloader после вызова родителя");
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
