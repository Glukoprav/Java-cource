package local.home.azav.java.hw9_serialization.proxycache;

import local.home.azav.java.hw9_serialization.annotationcache.Cache;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс кеширующего прокси.
 * Использует аннотацию Cache как признак кешируемого метода и реализует её атрибуты.
 * Пространство для кэширования:
 * - память - используется сбалансированное дерево TreeMap;
 * - файловая система - используется сериализуемый прокси.
 */
public class ProxyCache implements InvocationHandler {
    private final Object obj;
    private final Map<String, Object> cachedResults;

    public ProxyCache(Object o) {
        obj = o;
        // Кэш в памяти храним как сбалансированное дерево
        cachedResults = new TreeMap<>();
    }

    /**
     * Обработчик вызова метода в экземпляре прокси.
     * Проверяем наличие гоотового результата в пространстве кэша
     * и возвращает результат, или из кэша, или вызовом исходного
     * (кэшируемого) метода.
     */
    public Object invoke(Object proxy, Method method, Object[] arguments) throws InvocationTargetException, IllegalAccessException, IOException {
        Object result;
        // Берем имя метода
        String strMethod = method.getName();
        // Собираем аргументы вызванного метода в строку
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
                // Если нет значения в кэше, то вызываем исходный метод
                result = method.invoke(obj, arguments);
                // И результат берем в кэш
                insertInCache(strKey, result, annCache);
            }
        } else {
            // Если аннотации Cache нет, то вызываем исходный метод
            result = method.invoke(obj, arguments);
            System.out.println("Метод без аннотации: " + strMethod);
        }
        return result;
    }


    /**
     * Проверяет наличие значения в кэше, выбор пространства - по переданной аннотации.
     *
     * @param strKey ключ поиска (склеенные - имя метода и его аргументы).
     *               Для MEMORY - используется - ключ поиска.
     *               Для FILE - из аннотации путь и префикс + ключ поиска + расширение из аннотации.
     * @param cache  аннотация, назначенная исходному методу
     * @return результат, хранящийся в кэше, при отсутствии в кэше - вернет null.
     */
    private Object checkInCache(String strKey, Cache cache) {
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
                    // Если нашли файл - десериализуем
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

    /**
     * Вставка в кэш, выбор пространства - по переданной аннотации.
     *
     * @param strKey ключ поиска (склеенные - имя метода и его аргументы).
     *               Для MEMORY - используется - ключ поиска.
     *               Для FILE - из аннотации путь и префикс + ключ поиска + расширение из аннотации.
     * @param result вычисленный результат исходного метода, помещаемый в кэш.
     * @param cache  аннотация, назначенная исходному методу.
     */
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

    /**
     * Десериализация кэшированного результата
     * через сериализованный прокси KeySerial
     *
     * @param strFileName имя файла для десериализации.
     * @return результат, приведенный к типу сериализованного прокси.
     */
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

    /**
     * Сериализация результатата.
     *
     * @param strFileName имя файла для сериализации.
     * @param result      сериализуемый результат, приведенный к типу
     *                    сериализованного прокси KeySerial
     */
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
