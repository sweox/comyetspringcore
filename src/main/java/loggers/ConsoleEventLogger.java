package loggers;

import beans.Event;

/**
 * Created by BELSHINA on 28.11.2017.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
