package local.home.azav.java.hw7_classloaders.variant02.app;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classloader для класса AppCalc
 */
public class AppClassloader extends ApiClassloader {
    private static final Logger LOG = Logger.getLogger(AppClassloader.class.getName());

    private final String classPathApp;   // путь к классу

    AppClassloader(String classPathApp) {
        super(classPathApp);
        this.classPathApp = classPathApp;
        LOG.log(Level.INFO,"AppClassloader после вызова родителя");
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
