package local.home.azav.java.hw11_12_threadpools;

import local.home.azav.java.hw11_12_threadpools.scalablethreadpool.ScalableThreadPool;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static java.lang.String.format;

/**
 * Класс для проверки работы пула с переменным числом потоков.
 * Сначала запускаем и измеряем время работы задачи в одном потоке,
 * затем запускаем и измеряем время работы задачи в пуле потоков.
 */
public class MainScalableThreadPool {
    private static final int COUNT = 50;   // число расчетов, раскидываемых по потокам

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Создаем класс задачи
        MyTask task = new MyTask();

        // Старт замера времени по задаче без пула потоков
        // Пускаем задачу COUNT раз с разными параметрами на одном потоке
        long startOne = System.nanoTime();
        double valOne = 0;
        for (int i = 0; i < COUNT; i++) {
            valOne += task.count(i);
        }
        // Выводим однопоточный результат
        System.out.println(format("Работа в 1 поток:  %d сек., результат: %f",
                (System.nanoTime() - startOne) / (1000_000_000),
                valOne));

        // Создаем переменный пул потоков
        ScalableThreadPool scalablePool = new ScalableThreadPool(4, 16);
        // Стартуем пул
        scalablePool.start();
        // Старт замера времени по задаче с пулом потоков
        // Пускаем задачу COUNT раз с разными параметрами по разным потокам
        long startPool = System.nanoTime();
        for (int i = 0; i < COUNT; i++) {
            final int j = i;
            scalablePool.execute(() -> task.count(j));
        }
        // Собираем результаты потоков в окончательный результат
        double valuePool = 0;
        for (Future<Double> future : scalablePool.listFutures) {
            valuePool += future.get();
        }
        // Выводим многопоточный результат
        System.out.println(format("Работа многопоточная:  %d сек., результат: %f",
                (System.nanoTime() - startPool) / (1000_000_000),
                valuePool));
        scalablePool.shutdown();
    }
}
// Для COUNT = 400
/// Вывод программы на старой 2-х ядерной машине:
// Работа в 1 поток:  189 сек., результат: -247097399,870833
// Работа многопоточная:  158 сек., результат: -247097399,870833
//  Вывод программы на средней свежести 4-х ядерном ноуте:
// Работа в 1 поток:  90 сек., результат: -247097399,870833
// Работа многопоточная:  43 сек., результат: -247097399,870833

// Аналог задачи на PL/SQL, запущенной на том-же ноуте с сервером Oracle 10
// отработала за 37 сек (прагма параллельности не включалась).