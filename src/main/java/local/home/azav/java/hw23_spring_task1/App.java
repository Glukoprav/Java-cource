package local.home.azav.java.hw23_spring_task1;

/** Задача 1 освоения Spring, по Youtube-лекциям
 * https://www.youtube.com/watch?v=3wBteulZaAs&list=PL6jg6AGdCNaWF-sUH2QDudBRXo54zuN1t&index=1 */
public class App {
    private Client client;
    private ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(String msg) {

    }

    public static void main(String[] args) {

    }
}
