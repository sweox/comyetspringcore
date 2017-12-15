package loggers;

import beans.Event;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by BELSHINA on 30.11.2017.
 */
public class CombinedEventLogger extends AbstractLogger {

    private final Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for(EventLogger logger:loggers) {
            logger.logEvent(event);
        }
    }

    public Collection<EventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
    }
}
