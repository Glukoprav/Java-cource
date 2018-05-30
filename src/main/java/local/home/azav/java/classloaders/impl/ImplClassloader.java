package local.home.azav.java.classloaders.impl;

import local.home.azav.java.classloaders.api.ApiClassloader;

import java.net.URL;

public class ImplClassloader extends ApiClassloader {
    public ImplClassloader(URL[] urls) {
        super(urls);
    }
}
