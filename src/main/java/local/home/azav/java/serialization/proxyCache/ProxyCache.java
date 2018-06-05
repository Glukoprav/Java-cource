package local.home.azav.java.serialization.proxyCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class ProxyCache implements InvocationHandler {
    private final Object obj;
    private final Map<String, Object> cachedResults;

    public ProxyCache(Object o) {
        obj = o;
        // Кэш храним как сбалансированное дерево
        cachedResults = new TreeMap<>();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        String strMethod = method.getName();
        Object result;
        String strArgs = Arrays.toString(args);
        System.out.println("Ключ: "+ strArgs);
        if (cachedResults.containsKey(strArgs)) {
            // Если есть значение в кэше - возвращаем его
            result = cachedResults.get(strArgs);
            System.out.println("Кэшированный результат: " + result + ", метода: " + strMethod);
        } else {
            // Если нет значения в кэше, то вызываем метод и результат берем в кэш
            result = method.invoke(obj, args);
            cachedResults.put(strArgs, result);
            System.out.println("Взяли в кэш: " + result);
        }
        return result;
    }
}
