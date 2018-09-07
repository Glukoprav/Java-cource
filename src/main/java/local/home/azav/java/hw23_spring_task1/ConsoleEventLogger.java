package local.home.azav.java.hw23_spring_task1;

import java.util.logging.Level;
import java.util.logging.Logger;

class ConsoleEventLogger {
    private static final Logger LOG = Logger.getLogger(ConsoleEventLogger.class.getName());

    void logEvent(String msg) {
        LOG.log(Level.INFO, "Сообщение: {0}", msg);
    }
}
