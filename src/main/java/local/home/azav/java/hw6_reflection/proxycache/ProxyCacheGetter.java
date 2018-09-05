package local.home.azav.java.hw6_reflection.proxycache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProxyCacheGetter implements InvocationHandler {
    private static final Logger LOG = Logger.getLogger(ProxyCacheGetter.class.getName());
    private final Object obj;
    private final Map<String, Object> cachedResults;

    public ProxyCacheGetter(Object o) {
        obj = o;
        // Кэш храним как сбалансированное дерево
        cachedResults = new TreeMap<>();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        String strMethod = method.getName();
        Object result;
        String strArgs = Arrays.toString(args);
        LOG.log(Level.INFO,"Ключ: {0}", strArgs);
        if (cachedResults.containsKey(strArgs)) {
            // Если есть значение в кэше - возвращаем его
            result = cachedResults.get(strArgs);
            LOG.log(Level.INFO,"Кэшированный результат: {0}, метода: {1}", new Object[]{result, strMethod});
        } else {
            // Если нет значения в кэше, то вызываем метод и результат берем в кэш
            result = method.invoke(obj, args);
            cachedResults.put(strArgs, result);
            LOG.log(Level.INFO,"Взяли в кэш: {0}", result);
        }
        return result;
    }
}
