package local.home.azav.java.reflectionAll.getterCounter;

import local.home.azav.java.Person;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SimpleGetterCounter implements GetterCounter {

    /**
     * Возвращает количество геттеров в переданном классе.
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
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Возвращает список геттеров в переданном классе.
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
                        strArr.add(met.getName());
                    }
                }
            }
        }
        return strArr;
    }

    public static void main(String[] args) {
        SimpleGetterCounter sgc = new SimpleGetterCounter();
        System.out.println(sgc.calcGetterCount(Person.class));
        System.out.println(sgc.arrayGetterCount(Person.class));
    }
}