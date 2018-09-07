package local.home.azav.java.hw14_executionmanager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainExecManager {
    private static final Logger LOG = Logger.getLogger(MainExecManager.class.getName());

    public static void main(String[] args) {
        ExecutionManager manager = new ExecutionManagerImpl();

        Context context = manager.execute(() -> LOG.log(Level.INFO,"Все задачи выполнены"),
                () -> {
                    throw new ArithmeticException("Исключительная ситуация при выполнении задачи");
                },
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    LOG.log(Level.INFO,"2");
                },
                () -> {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    LOG.log(Level.INFO,"3");
                },
                () -> LOG.log(Level.INFO,"4"),
                () -> LOG.log(Level.INFO,"5")
        );

        context.interrupt();
        LOG.log(Level.INFO,"Количество прерванных задач: {0}", context.getInterruptedTaskCount());
        LOG.log(Level.INFO,"Количество задач, при выполнении которых произошло исключение: {0}", context.getFailedTaskCount());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LOG.log(Level.INFO,"Количество задач выполненных на данный момент: {0}", context.getCompletedTaskCount());
        LOG.log(Level.INFO,"Все задачи завершены? {0}", context.isFinished());

        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LOG.log(Level.INFO,"А теперь? {0}", context.isFinished());
    }
}

