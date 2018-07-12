package local.home.azav.java.hw14_executionmanager;

public interface ExecutionManager {
    /**
     * Задание:
     * Метод execute принимает массив тасков, это задания которые ExecutionManager
     * должен выполнять параллельно (в вашей реализации пусть будет в своем пуле потоков).
     * После завершения всех тасков должен выполниться callback (ровно 1 раз).
     * Метод execute – это неблокирующий метод, который сразу возвращает объект Context.
     *
     * @param callback выполняется после завершения всех тасков (ровно 1 раз).
     * @param tasks    массив тасков, которые ExecutionManager должен выполнять параллельно в своем пуле потоков.
     * @return возвращает объект Context.
     */
    Context execute(Runnable callback, Runnable... tasks);
}
