package local.home.azav.java.hw7_classloaders.variant01.impl;

import local.home.azav.java.hw7_classloaders.variant01.api.ApiClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classloader для класса CalculatorImpl
 */
public class ImplClassloader extends ApiClassloader {
    private static final Logger LOG = Logger.getLogger(ImplClassloader.class.getName());

    public ImplClassloader() {
        super();
        LOG.log(Level.INFO,"ImplClassloader после вызова родителя");
    }

    @Override
    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.hw7_classloaders.variant01.impl.CalculatorImpl");
    }
}
