package local.home.azav.java.hw9_serialization.main;

import local.home.azav.java.hw9_serialization.proxyCache.ProxyCache;
import local.home.azav.java.hw9_serialization.service.Service;
import local.home.azav.java.hw9_serialization.service.ServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Класс проверки работы кэширующего и сериализованного прокси
 */
public class Main {
    public static void main(String[] args) {
        ServiceImpl sgc = new ServiceImpl();
        InvocationHandler handler = new ProxyCache(sgc);
        Service proxy = (Service)
                Proxy.newProxyInstance(
                        Service.class.getClassLoader(),
                        new Class[]{Service.class},
                        handler);
        // Кэш в файле c расширением .ssk
        System.out.println("Результат: " + proxy.doHardWork01(100));
        System.out.println("Результат: " + proxy.doHardWork01(200));
        System.out.println("Результат: " + proxy.doHardWork01(100));
        // В память
        System.out.println("Результат: " + proxy.doHardWork02(300));
        System.out.println("Результат: " + proxy.doHardWork02(400));
        System.out.println("Результат: " + proxy.doHardWork02(300));
        // В память
        System.out.println("Результат: " + proxy.doHardWork03(500));
        System.out.println("Результат: " + proxy.doHardWork03(600));
        System.out.println("Результат: " + proxy.doHardWork03(500));
        System.out.println("Результат: " + proxy.doHardWork03(0));
        System.out.println("Результат: " + proxy.doHardWork03(-10));
        System.out.println("Результат: " + proxy.doHardWork03(-10));

        // Кэш в файле c расширением .sdd
        System.out.println("Результат: " + proxy.doHardWork04(-100));
        System.out.println("Результат: " + proxy.doHardWork04(200));
        System.out.println("Результат: " + proxy.doHardWork04(-100));
    }
}