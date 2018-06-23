package local.home.azav.java.hw13_taskCallable;

import java.util.concurrent.Callable;

public class Task<T> {

    private Callable<? extends T> callable;
    private volatile T result = null;
    private volatile TaskExecException errorCall = null;

    public Task(Callable<? extends T> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
    }

    public T get() throws TaskExecException {
        if (errorCall != null) throw errorCall;
        if (result == null) {
            synchronized (this) {
                if (result == null) {
                    try {
                        result = callable.call();
                        System.out.println("Вызов call сделал поток: " + Thread.currentThread().getName());
                        System.out.println("Результат: " + result.toString());
                    } catch (Exception e) {
                        errorCall = new TaskExecException("Не рассчитался результат Callable!", e);
                    }
                }
            }
        }
        return result;
    }
}