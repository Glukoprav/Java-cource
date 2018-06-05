package local.home.azav.java.serialization.service;

import local.home.azav.java.serialization.annotationCache.Cache;
import local.home.azav.java.serialization.annotationCache.CacheInSpace;

public interface Service {

    @Cache(value = CacheInSpace.FILE)
    long doHardWork01(long t);

    @Cache
    long doHardWork02(long t);

    @Cache(value = CacheInSpace.MEMORY)
    long doHardWork03(long t);
}
