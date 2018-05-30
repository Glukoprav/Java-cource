package local.home.azav.java.classloaders.app;

import local.home.azav.java.classloaders.api.ApiClassloader;

import java.net.URL;

public class AppClassloader extends ApiClassloader {
    public AppClassloader(URL[] urls) {
        super(urls);
    }
}
