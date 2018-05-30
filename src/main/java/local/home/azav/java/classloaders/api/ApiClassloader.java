package local.home.azav.java.classloaders.api;

import java.net.URL;
import java.net.URLClassLoader;

public class ApiClassloader extends ClassLoader {

    public ApiClassloader() {
        super();
        System.out.println("ApiClassloader после вызова родителя");
    }


}
