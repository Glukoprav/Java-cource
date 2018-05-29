package local.home.azav.java.reflectionAll.proxyCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class ProxyCacheGetter implements InvocationHandler {
    private final Object obj;
    private final Map<String, Object> cachedResults;

    public ProxyCacheGetter(Object o) {
        obj = o;
        // Кэш храним как сбалансированное дерево
        cachedResults = new TreeMap<String, Object>();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        String strMethod = method.getName();
        Object result;
        if (cachedResults.containsKey(args.toString())) {
            // Если есть значение в кэше - возвращаем его
            result = cachedResults.get(args.toString());
            System.out.println("Кэшированный результат: " + result + " метода:" + strMethod);
        } else {
            // Если нет значения в кэше, то вызываем метод и результат берем в кэш
            result = method.invoke(obj, args);
            cachedResults.put(args.toString(), result);
            System.out.println("Взяли в кэш: " + result);
        }
        return result;
    }
}
