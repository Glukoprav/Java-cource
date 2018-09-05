package local.home.azav.java.hw11_12_threadpools;

import local.home.azav.java.hw11_12_threadpools.fixedthreadpool.FixedThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Класс для проверки работы пула с фиксированниы числом потоков.
 * Сначала запускаем и измеряем время работы задачи в одном потоке,
 * затем запускаем и измеряем время работы задачи в пуле потоков.
 */
public class MainFixedThreadPool {
    private static final Logger LOG = Logger.getLogger(MainFixedThreadPool.class.getName());

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
        LOG.log(Level.INFO, "Работа в 1 поток:  {0} сек., результат: {1}",
                new Object[]{(System.nanoTime() - startOne) / (1000_000_000), valOne});

        // Создаем пул из 8 потоков
        FixedThreadPool fixPool = new FixedThreadPool(8);
        // Стартуем пул
        fixPool.start();

        // Старт замера времени по задаче с пулом потоков
        // Пускаем задачу COUNT раз с разными параметрами по разным потокам
        long startPool = System.nanoTime();
        for (int i = 0; i < COUNT; i++) {
            final int j = i;
            fixPool.execute(() -> task.count(j));
        }
        // Собираем результаты потоков в окончательный результат
        double valuePool = 0;
        for (Future<Double> future : fixPool.listFutures) {
            valuePool += future.get();
        }
        // Выводим многопоточный результат
        LOG.log(Level.INFO,"Работа многопоточная:  {0} сек., результат: {1}",
                new Object[]{(System.nanoTime() - startPool) / (1000_000_000), valuePool});
        fixPool.shutdown();
    }
}
// Для COUNT = 400
/// Вывод программы на старой 2-х ядерной машине:
// Работа в 1 поток:  191 сек., результат: -247097399,870833
// Работа многопоточная:  169 сек., результат: -247097399,870833
/// Вывод программы на средней свежести 4-х ядерном ноуте:
// Работа в 1 поток:  90 сек., результат: -247097399,870833
// Работа многопоточная:  32 сек., результат: -247097399,870833