package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.api.ApiClassloader;

import java.net.URL;

public class AppClassloader extends ApiClassloader {
    public AppClassloader() throws ClassNotFoundException {
        super();
        //this.loadClass("local.home.azav.java.classloaders.app.AppCalc");
        System.out.println("AppClassloader после вызова родителя");
    }
}
