package core.spring;

import core.beans.EventType;
import core.loggers.EventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by BELSHINA on 09.12.2017.
 */
@Configuration
public class LoggerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;



    @Bean
    public Collection<EventLogger> combinedLoggers() {
        Collection<EventLogger> loggers = new ArrayList<EventLogger>(2);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new EnumMap<EventType, EventLogger>(EventType.class);
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combinedEventLogger);
        return map;
    }


}
