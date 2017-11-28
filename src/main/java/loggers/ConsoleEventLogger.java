package loggers;

/**
 * Created by BELSHINA on 28.11.2017.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(String msg) {
        System.out.println(msg);
    }
}
