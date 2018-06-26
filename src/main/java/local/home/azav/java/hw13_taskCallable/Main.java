package local.home.azav.java.hw13_taskCallable;

import local.home.azav.java.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Класс проверки работы Task
 */
public class Main implements Callable<Person> {
    public static void main(String[] args) {
        // Создаем объект проверки
        Callable<Person> callable = new Main();
        //создаем список с Future, которые ассоциированы с Callable
        List<Future<Person>> list = new ArrayList<>();
        // Создаем наш Task
        Task task = new Task(callable);
        // заворачиваем в Supplier, чтобы принял экзекутор
        Supplier getValue = () -> {
            try {
                return task.get();
            } catch (Exception e) {
                System.out.println("Проблема с получением значения!");
                e.printStackTrace();
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
                try {
                    System.out.println("из Future: " + fut.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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
