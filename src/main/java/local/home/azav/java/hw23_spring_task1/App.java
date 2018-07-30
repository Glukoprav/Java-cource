package local.home.azav.java.hw23_spring_task1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Задача 1 освоения Spring, по Youtube-лекциям
 * https://www.youtube.com/watch?v=3wBteulZaAs&list=PL6jg6AGdCNaWF-sUH2QDudBRXo54zuN1t&index=1
 */
@Component
public class App {
    private Client client;
    private ConsoleEventLogger eventLogger;

    @Autowired
    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        App app;
//        app.client = new Client("1", "Fedia Van");
//        app.eventLogger = new ConsoleEventLogger();
//        app.logEvent("First probe event " + app.client.getId());

    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }
}
