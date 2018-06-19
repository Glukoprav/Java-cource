package local.home.azav.java.threadPools.fixedThreadPool;


import local.home.azav.java.threadPools.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Класс фиксированного числа потоков.
 * Количество потоков задается в конструкторе и не меняется
 */
public class FixedThreadPool implements ThreadPool {
    private final int colThread;
    ExecutorService threadPool;      // Пул потоков
    public List<Future> futures;     // Список с результатами работы потоков

    public FixedThreadPool(int colThread) {
        this.colThread = colThread;
        futures = new ArrayList<>();
    }

    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока не появится
     * новое задание в очереди (см. execute)
     */
    @Override
    public void start() {
        threadPool = Executors.newFixedThreadPool(colThread);
    }

    /**
     * Складывает задания в очередь. Освободившийся поток должен выполнить это задание.
     * Каждое задание должны быть выполнено ровно 1 раз
     */
    @Override
    public void execute(Supplier runnable) {
        futures.add(CompletableFuture.supplyAsync(runnable, threadPool));
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}