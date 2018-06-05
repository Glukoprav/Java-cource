package local.home.azav.java.serialization.proxyCache;

import local.home.azav.java.serialization.annotationCache.Cache;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class ProxyCache implements InvocationHandler {
    private final Object obj;
    private final Map<String, Object> cachedResults;

    public ProxyCache(Object o) {
        obj = o;
        // Кэш в памяти храним как сбалансированное дерево
        cachedResults = new TreeMap<>();
    }

    public Object invoke(Object proxy, Method method, Object[] arguments) throws InvocationTargetException, IllegalAccessException, IOException, ClassNotFoundException {
        Object result;
        String strMethod = method.getName();
        // Собираем аргументы метода в строку
        StringBuilder strBuilderArgs = new StringBuilder();
        for (Object arg : arguments) {
            strBuilderArgs.append(arg.toString());
        }
        String strArgs = strBuilderArgs.toString();
        // Ключем является: имя метода + все аргументы метода
        System.out.println("Ключ: " + strMethod + strArgs);
        String strKey = strMethod + strArgs;
        // Проверяем наличие аннотации Cache
        if (method.isAnnotationPresent(Cache.class)) {
            Cache annCache = method.getAnnotation(Cache.class);
            // Проверяем наличие в кэше
            result = checkInCache(strKey, annCache);
            if (result == null) {
                // Если нет значения в кэше, то вызываем метод и результат берем в кэш
                result = method.invoke(obj, arguments);
                insertInCache(strKey, result, annCache);
                System.out.println("Взяли в кэш: " + result);
            } else {
                System.out.println("Кэшированный результат: " + result + ", метода: " + strMethod);
            }
        } else {
            result = method.invoke(obj, arguments);

        }
        return result;
    }

    // Приватный класс для сериализации
    private class MethodSerial implements Serializable {
        private String method;
        private Object result;

        MethodSerial(String method, Object result) {
            this.method = method;
            this.result = result;
        }
    }

    // Проверяем наличие значения в кэше, выбор места - по переданной аннотации
    private Object checkInCache(String strKey, Cache cache) throws IOException, ClassNotFoundException {
        Object result;
        switch (cache.value()) {
            case MEMORY:
                result = cachedResults.get(strKey);
                break;
            case FILE:
                // Путь и имя файла: из аннотации путь и префикс + ключ + расширение из аннотации
                String strFileName = cache.pathFile() + cache.fileNamePrefix() + strKey + cache.fileExtension();
                if (Files.exists(Paths.get(strFileName))) {
                    MethodSerial ms = deserialMethod(strFileName);
                    result = ms.result;
                } else {
                    return null;
                }
                break;
            default:
                result = null;
        }
        return result;
    }

    // Вставка в кэш, выбор места - по переданной аннотации
    private void insertInCache(String strKey, Object result, Cache cache) throws IOException {
        switch (cache.value()) {
            case MEMORY:
                cachedResults.put(strKey, result);
                break;
            case FILE:
                // Путь и имя файла: из аннотации путь и префикс + ключ + расширение из аннотации
                String strFileName = cache.pathFile() + cache.fileNamePrefix() + strKey + cache.fileExtension();
                serialMethod(strFileName,strKey,result);
                break;
            default:
                result = null;
        }
    }

    private MethodSerial deserialMethod(String strFileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(strFileName);
             ObjectInputStream oin = new ObjectInputStream(fis)) {
            return (MethodSerial) oin.readObject();
        }
    }

    private void serialMethod(String strFileName, String strKey, Object result) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(strFileName);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            MethodSerial methodSerial = new MethodSerial(strKey, result);
            out.writeObject(methodSerial);
            out.flush();
        }
    }

}
