package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.api.ApiClassloader;

import java.net.URL;

public class AppClassloader extends ApiClassloader {
    public AppClassloader(URL[] urls) throws ClassNotFoundException {
        super(urls);
        //this.loadClass("local.home.azav.java.classloaders.app.AppCalc");
        System.out.println("AppClassloader после вызова родителя");
    }
}
