package local.home.azav.java.hw6_reflection.gettercounter;

import local.home.azav.java.hw2_person.Person;
import local.home.azav.java.hw6_reflection.annotationskip.PersonAnnotation;
import local.home.azav.java.hw6_reflection.annotationskip.Skip;
import local.home.azav.java.hw6_reflection.proxycache.ProxyCacheGetter;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleGetterCounter implements GetterCounter {
    private static final Logger LOG = Logger.getLogger(SimpleGetterCounter.class.getName());

    /**
     * Возвращает количество геттеров в переданном классе.
     *
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    @Override
    public int calcGetterCount(Class<?> clazz) {
        if (clazz == null) {
            return 0;
        }
        Method[] methodsClass = clazz.getMethods();
        int count = 0;
        for (Method met : methodsClass) {
            if (!met.getName().startsWith("get") || !met.getName().startsWith("is")) continue;
            if (met.getParameterTypes().length > 0) continue;
            if (void.class.equals(met.getReturnType())) continue;
            if (!met.isAnnotationPresent(Skip.class)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Возвращает список геттеров в переданном классе.
     *
     * @param clazz класс в котором необходимо прочитать геттеры.
     * @return возвращает список строк - имена найденных геттеров.
     */
    public List<String> arrayGetterCount(Class<?> clazz) {
        Method[] methodsClass = clazz.getMethods();
        List<String> strArr = new ArrayList<>();
        for (Method met : methodsClass) {
            if (!met.getName().startsWith("get") || !met.getName().startsWith("is")) continue;
            if (met.getParameterTypes().length > 0) continue;
            if (void.class.equals(met.getReturnType())) continue;
            if (!met.isAnnotationPresent(Skip.class)) {
                strArr.add(met.getName());
            }
        }
        return strArr;
    }

    /**
     * Проверка работы метода calcGetterCount,
     * аннотации @Skip,
     * и кэширующего прокси, повешенного на метод calcGetterCount
     */
    public static void main(String[] args) {
        SimpleGetterCounter sgc = new SimpleGetterCounter();
        InvocationHandler handler = new ProxyCacheGetter(sgc);
        GetterCounter proxy = (GetterCounter)
                Proxy.newProxyInstance(
                        GetterCounter.class.getClassLoader(),
                        new Class[]{GetterCounter.class},
                        handler);
        LOG.log(Level.INFO,"proxy PersonAnnotation 1 > {0}", proxy.calcGetterCount(PersonAnnotation.class));
        LOG.log(Level.INFO,"proxy PersonAnnotation 2 > {0}", proxy.calcGetterCount(PersonAnnotation.class));
        LOG.log(Level.INFO, "proxy Person > {0}", proxy.calcGetterCount(Person.class));
        LOG.log(Level.INFO, "sgc PersonAnnotation 2 > {0}", sgc.arrayGetterCount(PersonAnnotation.class));
        LOG.log(Level.INFO, "sgc PersonAnnotation 2 > {0}", sgc.arrayGetterCount(Person.class));
    }
}