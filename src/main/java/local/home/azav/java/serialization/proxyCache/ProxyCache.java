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
            }
        } else {
            result = method.invoke(obj, arguments);
            System.out.println("Метод без аннотации: " + strMethod);
        }
        return result;
    }


    // Проверяем наличие значения в кэше, выбор места - по переданной аннотации
    private Object checkInCache(String strKey, Cache cache) throws IOException, ClassNotFoundException {
        Object result;
        switch (cache.value()) {
            case MEMORY:
                result = cachedResults.get(strKey);
                System.out.println("Результат из кэша ПАМЯТИ: " + result + ", по ключу: " + strKey);
                break;
            case FILE:
                // Путь и имя файла: из аннотации путь и префикс + ключ + расширение из аннотации
                String strFileName = cache.pathFile() + cache.fileNamePrefix() + strKey + cache.fileExtension();
                if (Files.exists(Paths.get(strFileName))) {
                    Object ks = deserialMethod(strFileName);
                    result = ((KeySerial) ks).getResult();
                    String keyMethod = ((KeySerial) ks).getMethod();
                    System.out.println("Результат из ФАЙЛОВОГО кэша: " + result + ", по ключу: " + keyMethod);
                } else {
                    return null;
                }
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    // Вставка в кэш, выбор места - по переданной аннотации
    private void insertInCache(String strKey, Object result, Cache cache) throws IOException {
        switch (cache.value()) {
            case MEMORY:
                cachedResults.put(strKey, result);
                System.out.println("Взяли в кэш ПАМЯТИ: " + result);
                break;
            case FILE:
                // Путь и имя файла: из аннотации путь и префикс + ключ + расширение из аннотации
                String strFileName = cache.pathFile() + cache.fileNamePrefix() + strKey + cache.fileExtension();
                // Создаем объект кэширования для SerializationProxy
                KeySerial keySerial = new KeySerial(strKey, result);
                serialMethod(strFileName, keySerial);
                System.out.println("Взяли в ФАЙЛОВЫЙ кэш: " + result);
                break;
            default:
                throw new IOException("Неизвестное значение аннотации!");
        }
    }

    private KeySerial deserialMethod(String strFileName) {
        KeySerial obje;
        try (FileInputStream fis = new FileInputStream(strFileName);
             ObjectInputStream oin = new ObjectInputStream(fis)) {
            obje = (KeySerial) oin.readObject();
            return obje;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obje = null;
    }

    private void serialMethod(String strFileName, Object result) {
        try (FileOutputStream fos = new FileOutputStream(strFileName);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(result);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
