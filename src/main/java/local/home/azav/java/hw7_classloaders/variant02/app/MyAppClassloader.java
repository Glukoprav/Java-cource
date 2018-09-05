package local.home.azav.java.hw7_classloaders.variant02.app;

import local.home.azav.java.hw7_classloaders.variant02.api.ApiClassloader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classloader для класса MyApp
 */
public class MyAppClassloader extends ApiClassloader {
    private static final Logger LOG = Logger.getLogger(MyAppClassloader.class.getName());

    private final String classPathMy;   // путь к классу

    MyAppClassloader(String classPathMy) {
        super(classPathMy);
        this.classPathMy = classPathMy;
        LOG.log(Level.INFO,"MyAppClassloader после вызова родителя");
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
