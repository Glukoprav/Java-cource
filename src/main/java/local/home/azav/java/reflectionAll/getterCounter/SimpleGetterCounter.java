package local.home.azav.java.reflectionAll.getterCounter;

import com.sun.deploy.net.proxy.ProxyUtils;
import local.home.azav.java.reflectionAll.annotationSkip.PersonAnnotation;
import local.home.azav.java.reflectionAll.annotationSkip.Skip;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SimpleGetterCounter implements GetterCounter {

    /**
     * Возвращает количество геттеров в переданном классе.
     *
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    @Override
    public int calcGetterCount(Class<?> clazz) {
        Method[] methodsClass = clazz.getMethods();
        int count = 0;
        for (Method met : methodsClass) {
            if (met.getName().startsWith("get")) {
                if (met.getParameterTypes().length == 0) {
                    if (!void.class.equals(met.getReturnType())) {
                        if (!met.isAnnotationPresent(Skip.class)) {
                            count++;
                        }
                    }
                }
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
            if (met.getName().startsWith("get")) {
                if (met.getParameterTypes().length == 0) {
                    if (!void.class.equals(met.getReturnType())) {
                        if (!met.isAnnotationPresent(Skip.class)) {
                            strArr.add(met.getName());
                        }
                    }
                }
            }
        }
        return strArr;
    }

    public static void main(String[] args) {
        SimpleGetterCounter sgc = new SimpleGetterCounter();
        //SimpleGetterCounter cashSgc = ProxyUtils;
        System.out.println(sgc.calcGetterCount(PersonAnnotation.class));
        System.out.println(sgc.arrayGetterCount(PersonAnnotation.class));
    }
}