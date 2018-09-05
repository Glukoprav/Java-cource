package local.home.azav.java.hw7_classloaders.variant01.app;

import local.home.azav.java.hw7_classloaders.variant01.api.ApiClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classloader для класса AppCalc
 */
public class AppClassloader extends ApiClassloader {
    private static final Logger LOG = Logger.getLogger(AppClassloader.class.getName());

    public AppClassloader() {
        super();
        LOG.log(Level.INFO,"AppClassloader после вызова родителя");
    }

    @Override
    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.hw7_classloaders.variant01.app.AppCalc");
    }


}
