package loggers;

import beans.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BELSHINA on 29.11.2017.
 */
public class CacheFileEventLogger extends FileEventLogger {
    private int cachSize;
    private List<Event> cache;

    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cachSize) {
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


    public CacheFileEventLogger(String fileName, int cachSize) {
        super(fileName);
        this.cachSize = cachSize;
        cache = new ArrayList<Event>(cachSize);
    }

    public void destroy() {
        if(!cache.isEmpty()) {
            writeEventsFromCache();
            System.out.println("Events has been cached after destroy");
        }
    }
}
