package local.home.azav.java.classloaders.api;

import java.net.URL;
import java.net.URLClassLoader;

public class ApiClassloader extends URLClassLoader {

    public ApiClassloader(URL[] urls) {
        super(urls);
        System.out.println("ApiClassloader после вызова родителя");
    }
}
