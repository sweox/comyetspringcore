package loggers;

import beans.Event;

/**
 * Created by BELSHINA on 28.11.2017.
 */
public interface EventLogger {
    public void logEvent(Event event);

    public String getName();
}
