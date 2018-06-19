package local.home.azav.java.threadPools;

/**
 * Класс с нагрузочной задачей
 */
public class MyTask {
    public Double count(double a) {
        for (int i = 0; i < 1000000; i++) {
            a = a + Math.tan(a);
        }
        return a;
    }
}
