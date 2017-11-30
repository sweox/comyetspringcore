package loggers;

import beans.Event;

import java.util.Collection;
import java.util.List;

/**
 * Created by BELSHINA on 30.11.2017.
 */
public class CombinedEventLogger implements EventLogger {

    private Collection<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for(EventLogger logger:loggers) {
            logger.logEvent(event);
        }
    }
}
