package local.home.azav.java.hw7_classloaders.variant01.api;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ApiClassloader extends ClassLoader {
    private static final Logger LOG = Logger.getLogger(ApiClassloader.class.getName());

    public ApiClassloader() {
        super();
        LOG.log(Level.INFO,"ApiClassloader после вызова родителя");
    }

    public Class<?> loadClass() throws ClassNotFoundException {
        return super.loadClass("local.home.azav.java.hw7_classloaders.variant01.api.ICalculator");
    }
}
