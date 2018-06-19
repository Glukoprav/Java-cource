package local.home.azav.java.threadPools;

import local.home.azav.java.threadPools.fixedThreadPool.FixedThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.lang.String.format;

public class MainFixedThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Создаем класс задачи
        MyTask task = new MyTask();

        // Старт замера времени по задаче без пула потоков
        // Пускаем задачу 400 раз с разными параметрами на одном потоке
        long start1 = System.nanoTime();
        double value1 = 0;
        for (int i = 0; i < 400; i++) {
            value1 += task.count(i);
        }
        // Выводим однопоточный результат
        System.out.println(format("Работала:  %d сек., результат: %f",
                (System.nanoTime() - start1) / (1000_000_000),
                value1));

        // Создаем пул из 8 потоков
        FixedThreadPool fixPool = new FixedThreadPool(8);
        // Стартуем пул
        fixPool.start();

        // Старт замера времени по задаче с пулом потоков
        // Пускаем задачу 400 раз с разными параметрами по разным потокам
        long start2 = System.nanoTime();
        for (int i = 0; i < 400; i++) {
            final int j = i;
            fixPool.execute(() -> task.count(j));
        }
        // Собираем результаты потоков в окончательный результат
        double value2 = 0;
        for (Future<Double> future : fixPool.futures) {
            value2 += future.get();
        }
        // Выводим многопоточный результат
        System.out.println(format("Работала:  %d сек., результат: %f",
                (System.nanoTime() - start2) / (1000_000_000),
                value2));
        fixPool.shutdown();
    }
}

// Вывод программы на старой 2-х ядерной машине:
// Работала:  191 сек., результат: -247097399,870833
// Работала:  169 сек., результат: -247097399,870833