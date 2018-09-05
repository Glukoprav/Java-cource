package local.home.azav.java.hw7_classloaders.variant01.app;

import local.home.azav.java.hw7_classloaders.variant01.api.ApiClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classloader для класса MyApp
 */
public class MyAppClassloader extends ApiClassloader {
    private static final Logger LOG = Logger.getLogger(MyAppClassloader.class.getName());

    public MyAppClassloader() {
        super();
        LOG.log(Level.INFO,"MyAppClassloader после вызова родителя");
    }

    @Override
    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.hw7_classloaders.variant01.app.MyApp");
    }
}
