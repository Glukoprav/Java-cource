package local.home.azav.java.hw14_executionManager;

public class MainExecManager {
        public static void main(String[] args) {
            ExecutionManager manager = new ExecutionManagerImpl();

            Context context = manager.execute(() -> System.out.println("Все задачи выполнены"),
                    () -> {
                        throw new RuntimeException("Исключительная ситуация при выполнении задачи");
                    },
                    () -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("2");
                    },
                    () -> {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("3");
                    },
                    () -> System.out.println("4"),
                    () -> System.out.println("5")
            );

//            Runnable[] tasks = new Runnable[]{
//                    new Runnable() {
//                        public void run() {
//                            System.out.println("task1");
//                        }
//                    },
//                    new Runnable() {
//                        public void run() {
//                            System.out.println("task2");
//                        }
//                    },
//                    new Runnable() {
//                        public void run() {
//                            System.out.println("task3");
//                        }
//                    },
//                    new Runnable() {
//                        public void run() {
//                            System.out.println("task4");
//                        }
//                    }
//            };
//
//            Runnable callback = () -> System.err.println("\nCallback run now!\n");
//
//            ExecutionManagerImpl executionManager = new ExecutionManagerImpl();
//            executionManager.execute(callback,tasks);

            context.interrupt();
            System.out.println("Количество прерванных задач: " + context.getInterruptedTaskCount());

            System.out.println("Количество задач, при выполнении которых произошло исключение: " + context.getFailedTaskCount());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Количество задач выполненных на данный момент:" + context.getCompletedTaskCount());
            System.out.println("Все задачи завершены? " + context.isFinished());

            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("А теперь? " + context.isFinished());


        }
    }

