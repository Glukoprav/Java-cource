package local.home.azav.java.hw14_executionmanager;

public class ExecutionManagerImpl implements ExecutionManager {
    private Context context;
    //ExecutorService threadPool;

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
//        ExecutorService threadPool =  Executors.newFixedThreadPool(10);
//
//        for (Runnable task : tasks) {
//            threadPool.execute(task);
//        }
//
//        threadPool.execute(callback);
        return new ContextImpl(callback, tasks);
    }

//    public void shutdown() {
//        threadPool.shutdown();
//    }
}
