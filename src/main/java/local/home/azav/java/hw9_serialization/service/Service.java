package local.home.azav.java.hw9_serialization.service;

import local.home.azav.java.hw9_serialization.annotationCache.Cache;
import local.home.azav.java.hw9_serialization.annotationCache.CacheInSpace;

/**
 * Интерфейс, использующий аннотацию @Cache для обозначения,
 * по каким методам и куда - кэшировать результаты вызовов методов.
 * Используются различные атрибуты аннотации @Cache.
 */
public interface Service {

    @Cache(value = CacheInSpace.FILE, fileExtension = ".ssm")
    long doHardWork01(long t);

    @Cache
    long doHardWork02(long t);

    @Cache(value = CacheInSpace.MEMORY)
    long doHardWork03(long t);

    @Cache(value = CacheInSpace.FILE, fileNamePrefix = "test", fileExtension = ".sdd")
    long doHardWork04(long t);
}
