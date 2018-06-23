package local.home.azav.java.hw11_12_threadPools.scalableThreadPool;

import local.home.azav.java.hw11_12_threadPools.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Класс переменного числа потоков.
 * В конструкторе задается минимальное и максимальное(intmin, intmax) число потоков.
 * Количество запущенных потоков может быть увеличено от минимального к максимальному,
 * если при добавлении нового задания в очередь - нет свободного потока для исполнения этого задания.
 * При отсутствии задания в очереди, количество потоков опять должно быть уменьшено до значения min
 */
public class ScalableThreadPool implements ThreadPool {
    private final int minNumThread;  // Минимальноее число потоков
    private final int maxNumThread;  // Максимальноее число потоков
    ExecutorService threadPool;      // Пул потоков
    public List<Future> listFutures; // Список для результатов работы потоков

    public ScalableThreadPool(int minNumThread, int maxNumThread) {
        super();
        this.minNumThread = minNumThread;
        this.maxNumThread = maxNumThread;
        listFutures = new ArrayList<>();
    }

    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока не появится
     * новое задание в очереди (см. execute)
     */
    @Override
    public void start() {
        threadPool = new ThreadPoolExecutor(minNumThread, maxNumThread, 1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    /**
     * Складывает задания в очередь. Освободившийся поток должен выполнить это задание.
     * Каждое задание должны быть выполнено ровно 1 раз
     */
    @Override
    public void execute(Supplier runnable) {
        listFutures.add(CompletableFuture.supplyAsync(runnable, threadPool));
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}