package local.home.azav.java.reflectionAll.proxyCache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class ProxyCacheGetter implements InvocationHandler {
    private final Object obj;
    private final Map<Integer, Integer> cachedResults;

    public ProxyCacheGetter(Object o) {
        obj = o;
        cachedResults = new TreeMap<Integer, Integer>();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Метод: " + method.getName());
        if (cachedResults.containsKey(args)) {
            //result =  cachedResults.get(inputData);
        } else {
            return method.invoke(obj, args);
        }

        // Если такой метод с такими параметрами не вызывался - вызываем метод, а результат вместе
        // с ключом помещаем в кэш
//        if (!cachedResults.containsKey(inputData)){
//            result =  method.invoke(inst, args);
//            cachedResults.put(inputData, result);
//            System.out.println("Вызванный метод будет помещен в кэш");
//        }
//        // Если по каким-то причинам в кэше хранится null - возвращаем 0
//        else if (cachedResults.get(inputData) == null) {
//            System.out.print("Возвращаем значение из кэша: ");
//            result = 0;
//        }   else{
//            System.out.print("Возвращаем значение из кэша: ");
//            // в других случаях возвращаем значение из кэша
//            result =  cachedResults.get(inputData);
//        }
        return 0;
    }
}
