package local.home.azav.java.hw14_executionmanager;

import java.util.ArrayList;
import java.util.List;

public class ContextImpl implements Context {
    private volatile int completedThreads = 0;
    private volatile int failedThreads = 0;
    private volatile int interruptedThreads = 0;
    private List<Thread> threadsList = new ArrayList<>();

    public ContextImpl(Runnable callback, Runnable... tasks) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            synchronized (threadsList) {
                failedThreads++;
            }
        });
        for (Runnable task : tasks) {
            Thread t = new Thread(() -> {
                if (Thread.interrupted()) {
                    synchronized (threadsList) {
                        interruptedThreads++;
                    }
                    return;
                }
                task.run();
                synchronized (threadsList) {
                    completedThreads++;
                }
            });
            threadsList.add(t);
            t.start();
        }
        while(true)
            if (this.isFinished())
                break;
        new Thread(() -> {
            callback.run();
        }).start();
    }

    @Override
    public int getCompletedTaskCount() {
        return completedThreads;
    }

    @Override
    public int getFailedTaskCount() {
        return failedThreads;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedThreads;
    }

    @Override
    public void interrupt() {
        threadsList
                .stream()
                .forEach(t -> t.interrupt());
    }

    @Override
    public boolean isFinished() {
        return threadsList.size() - interruptedThreads - completedThreads - failedThreads == 0;
    }
}
