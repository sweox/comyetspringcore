package loggers;

import beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BELSHINA on 29.11.2017.
 */
@Component
public class CacheFileEventLogger extends FileEventLogger {

    // Use system property cache.size or 5 if property is not set
    @Value("${cache.size:5}")
    private int cacheSize;
    private List<Event> cache;

    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
            System.out.println("Events has been cached after clear list");
        }
    }

    private void writeEventsFromCache() {
        for(Event event:cache) {
            super.logEvent(event);
        }
    }

    public CacheFileEventLogger() {
    }

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @PreDestroy
    public void destroy() {
        if(!cache.isEmpty()) {
            writeEventsFromCache();
            System.out.println("Events has been cached after destroy");
        }
    }
}
