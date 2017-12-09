package loggers;

import beans.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by BELSHINA on 28.11.2017.
 */

@Component
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
