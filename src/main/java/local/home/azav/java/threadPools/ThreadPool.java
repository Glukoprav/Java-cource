package local.home.azav.java.threadPools;

import java.util.function.Supplier;

/**
 * Интерфейс для реализации TreadPool
 */
public interface ThreadPool {

    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока не появится
     * новое задание в очереди (см. execute)
     */
    void start();

    /**
     * Складывает задания в очередь. Освободившийся поток должен выполнить это задание.
     * Каждое задание должны быть выполнено ровно 1 раз
     */
    void execute(Supplier runnable);
}
