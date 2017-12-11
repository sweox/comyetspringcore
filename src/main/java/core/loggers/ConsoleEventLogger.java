package core.loggers;

import core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by BELSHINA on 28.11.2017.
 */

@Component
public class ConsoleEventLogger extends AbstractLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

    @Value("Console logger")
    @Override
    protected void setName(String name) {
        this.name = name;
    }
}
