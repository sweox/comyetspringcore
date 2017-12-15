package core.loggers;

import core.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by BELSHINA on 30.11.2017.
 */
@Component
public class CombinedEventLogger extends AbstractLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

//    public CombinedEventLogger(List<EventLogger> core.loggers) {
//        this.core.loggers = core.loggers;
//    }

    public void logEvent(Event event) {
        for(EventLogger logger:loggers) {
            logger.logEvent(event);
        }
    }

    public Collection<EventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
    }

    @Value("#{'Combined ' + combinedLoggers.![name].toString()}")
    @Override
    protected void setName(String name) {
        this.name = name;
    }

}
