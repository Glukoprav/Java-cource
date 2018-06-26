package local.home.azav.java.hw14_executionManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutionManagerImpl implements ExecutionManager {
    private Context context;
    ExecutorService threadPool;

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ExecutorService threadPool =  Executors.newFixedThreadPool(10);

        for (Runnable task : tasks) {
            threadPool.execute(task);
        }

        threadPool.execute(callback);
        return context;
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}
