package local.home.azav.java.hw9_serialization.main;

import local.home.azav.java.hw9_serialization.proxycache.ProxyCache;
import local.home.azav.java.hw9_serialization.service.Service;
import local.home.azav.java.hw9_serialization.service.ServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Класс проверки работы кэширующего и сериализованного прокси
 */
public class Main {

    private static void outer(long str) {
        System.out.println("Результат: " + str);
    }

    public static void main(String[] args) {
        ServiceImpl sgc = new ServiceImpl();
        InvocationHandler handler = new ProxyCache(sgc);
        Service proxy = (Service)
                Proxy.newProxyInstance(
                        Service.class.getClassLoader(),
                        new Class[]{Service.class},
                        handler);
        // Кэш в файле c расширением .ssk
        outer(proxy.doHardWork01(100));
        outer(proxy.doHardWork01(200));
        outer(proxy.doHardWork01(100));
        // В память
        outer(proxy.doHardWork02(300));
        outer(proxy.doHardWork02(400));
        outer(proxy.doHardWork02(300));
        // В память
        outer(proxy.doHardWork03(500));
        outer(proxy.doHardWork03(600));
        outer(proxy.doHardWork03(500));
        outer(proxy.doHardWork03(0));
        outer(proxy.doHardWork03(-10));
        outer(proxy.doHardWork03(-10));

        // Кэш в файле c расширением .sdd
        outer(proxy.doHardWork04(-100));
        outer(proxy.doHardWork04(200));
        outer(proxy.doHardWork04(-100));
    }
}