package loggers;

import beans.Event;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created by BELSHINA on 30.11.2017.
 */
@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

//    public CombinedEventLogger(List<EventLogger> loggers) {
//        this.loggers = loggers;
//    }

    public void logEvent(Event event) {
        for(EventLogger logger:loggers) {
            logger.logEvent(event);
        }
    }
}
