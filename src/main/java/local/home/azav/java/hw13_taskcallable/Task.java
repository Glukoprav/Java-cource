package local.home.azav.java.hw13_taskcallable;

import java.util.concurrent.Callable;


/**
 *     Задание:
 * Данный класс в конструкторе принимает экземпляр java.util.concurrent.Callable.
 * Ваша задача реализовать метод get(), который возвращает результат работы Callable.
 * Выполнение callable должен начинать тот поток, который первый вызвал метод get().
 * Если несколько потоков одновременно вызывают этот метод, то выполнение должно
 * начаться только в одном потоке, а остальные должны ожидать конца выполнения (не нагружая процессор).
 * Если при вызове get() результат уже просчитан, то он должен вернуться сразу,
 * (даже без задержек на вход в синхронизированную область).
 * Если при просчете результата произошел Exception, то всем потокам при вызове get(),
 * надо кидать этот Exception, обернутый в ваш RuntimeException
 * (подходящее название своему ексепшену придумайте сами).
 */
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
        System.out.println("Поток: " + Thread.currentThread().getName() + " с результатом: " + result.toString());
        return result;
    }
}