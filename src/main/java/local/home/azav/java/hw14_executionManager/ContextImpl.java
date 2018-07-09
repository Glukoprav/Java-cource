package local.home.azav.java.hw14_executionManager;

import java.util.ArrayList;
import java.util.List;

public class ContextImpl implements Context {
    private volatile int failedNumber = 0;
    private volatile int completedNumber = 0;
    private volatile int interruptedNumber = 0;
    private List<Thread> threadsList = new ArrayList<>();

    @Override
    public int getCompletedTaskCount() {
        return completedNumber;
    }

    @Override
    public int getFailedTaskCount() {
        return failedNumber;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedNumber;
    }

    @Override
    public void interrupt() {
        threadsList
                .stream()
                .forEach(t -> t.interrupt());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
