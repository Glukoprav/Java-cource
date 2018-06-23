package local.home.azav.java.hw13_taskCallable;

import local.home.azav.java.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Класс проверки работы Task
 */
public class Main implements Callable<Person> {
    public static void main(String[] args) {
        // Создаем экзекутор с фиксированным числом потоков
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // Создаем объект проверки
        Callable<Person> callable = new Main();
        //создаем список с Future, которые ассоциированы с Callable
        List<Future<Person>> list = new ArrayList<>();
        // Создаем наш Task
        Task task = new Task(callable);
        Runnable getValue = () -> {
            try {
                task.get();
                //System.out.println(task.get());
            } catch (Exception e) {
                System.out.println("Проблема с получением значения!");
                e.printStackTrace();
            }
        };
        for(int i=0; i< 15; i++){
            //сабмитим таски, которые будут выполнены пулом потоков
            Future<Person> future = (Future<Person>) executor.submit(getValue);
            //добавляя Future в список мы сможем получить результат выполнения
            list.add(future);
        }
        for(Future<Person> fut : list) {
            try {
                try {
                    System.out.println(fut.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    @Override
    public Person call() throws Exception {
        // спим секунду
        Thread.sleep(1000);
        // и для примера возвращаем новую персону
        return new Person(true, "Вася", 25);
    }
}
