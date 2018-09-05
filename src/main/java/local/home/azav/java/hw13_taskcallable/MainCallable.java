package local.home.azav.java.hw13_taskcallable;

import local.home.azav.java.hw2_person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс проверки работы Task
 */
public class MainCallable implements Callable<Person> {
    private static final Logger LOG = Logger.getLogger(MainCallable.class.getName());

    public static void main(String[] args) {
        // Создаем объект проверки
        Callable<Person> callable = new MainCallable();
        //создаем список с Future, которые ассоциированы с Callable
        List<Future<Person>> list = new ArrayList<>();
        // Создаем наш Task
        Task task = new Task(callable);
        // заворачиваем в Supplier, чтобы принял экзекутор
        Supplier getValue = () -> {
            try {
                return task.get();
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Проблема с получением значения! ", e);
                return null;
            }
        };
        // Создаем экзекутор с фиксированным числом потоков
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // Сапливим таски для экзекутора, которые будут выполнены пулом потоков и закинуты в список из Future
        for (int i = 0; i < 16; i++) {
            list.add(CompletableFuture.supplyAsync(getValue, executor));
        }
        // Распечатаем список значений, полученных потоками
        for (Future<Person> fut : list) {
            try {
                LOG.log(Level.INFO,"из Future: {0}", fut.get());
            } catch (ExecutionException e) {
                LOG.log(Level.SEVERE, "Exception: ", e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        executor.shutdown();
    }

    // Задача, вызываемая из Task.get
    // Создаем новую персону, которую и вернем всем потокам
    @Override
    public Person call() throws Exception {
        // для примера возвращаем новую персону
        return new Person(true, "Вася П.", 25);
    }
}
