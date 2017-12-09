package core.loggers;

import core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by BELSHINA on 29.11.2017.
 */

@Component
public class FileEventLogger implements EventLogger {
    @Value("${events.file:target/events_log.txt}")
    String fileName;

    private File file;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString()+"\r\n" , true);
        } catch (IOException e) {e.printStackTrace();}
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(fileName);

        if(file.exists() && !file.canWrite()) {
            throw new IOException("Can't write in this file" + fileName);
        }
    }
}
