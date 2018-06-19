package local.home.azav.java.threadPools.ScalableThreadPool;

import local.home.azav.java.threadPools.ThreadPool;

import java.util.function.Supplier;

/**
 * Класс переменного числа потоков.
 * В конструкторе задается минимальное и максимальное(intmin, intmax) число потоков.
 * Количество запущенных потоков может быть увеличено от минимального к максимальному,
 * если при добавлении нового задания в очередь - нет свободного потока для исполнения этого задания.
 * При отсутствии задания в очереди, количество потоков опять должно быть уменьшено до значения min
 */
public class ScalableThreadPool implements ThreadPool {

    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока не появится
     * новое задание в очереди (см. execute)
     */
    @Override
    public void start() {

    }

    /**
     * Складывает задания в очередь. Освободившийся поток должен выполнить это задание.
     * Каждое задание должны быть выполнено ровно 1 раз
     */
    @Override
    public void execute(Supplier runnable) {

    }
}


//        import java.util.concurrent.BlockingQueue;
//        import java.util.concurrent.LinkedBlockingQueue;
//        import java.util.concurrent.atomic.AtomicInteger;

//public class ScalableThreadPool implements ThreadPool {
//
//    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
//    private final int minThreadsCount;
//    private final int maxThreadsCount;
//    private final AtomicInteger currentThreadsCount;
//
//    public ScalableThreadPool(int minThreadsCount, int maxThreadsCount) {
//        this.minThreadsCount = minThreadsCount;
//        this.maxThreadsCount = maxThreadsCount;
//        currentThreadsCount = new AtomicInteger();
//    }
//
//    @Override
//    public void start() {
//        while (currentThreadsCount.get() < minThreadsCount) {
//            new Worker().start();
//            currentThreadsCount.incrementAndGet();
//        }
//    }
//
//    @Override
//    public void execute(Runnable runnable) {
//        try {
//            tasks.put(runnable);
//            if (tasks.size() > currentThreadsCount.get()
//                    && currentThreadsCount.get() < maxThreadsCount) {
//                new Worker().start();
//                currentThreadsCount.incrementAndGet();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException("Interrupted exception: " + e.getMessage(), e);
//        }
//    }
//
//    private class Worker extends Thread {
//        @Override
//        public void run() {
//            while (true) {
//                if (tasks.isEmpty() && currentThreadsCount.get() > minThreadsCount) {
//                    this.interrupt();
//                    currentThreadsCount.decrementAndGet();
//                    return;
//                } else {
//                    try {
//                        tasks.take().run();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException("Interrupted exception: " + e.getMessage(), e);
//                    }
//                }
//            }
//        }
//    }
//}