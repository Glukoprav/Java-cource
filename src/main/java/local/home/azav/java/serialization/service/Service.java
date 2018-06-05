package local.home.azav.java.serialization.service;

import local.home.azav.java.serialization.annotationCache.Cache;
import local.home.azav.java.serialization.annotationCache.CacheInSpace;

public interface Service {

    @Cache(value = CacheInSpace.FILE)
    long doHardWork01();

    @Cache
    long doHardWork02();

    @Cache(value = CacheInSpace.MEMORY)
    long dohardWork03();
}
