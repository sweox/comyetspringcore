package loggers;

import beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by BELSHINA on 29.11.2017.
 */
public class FileEventLogger implements EventLogger {
    String fileName;
    File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString()+"\r\n" , true);
        } catch (IOException e) {e.printStackTrace();}
    }

    public void init() throws IOException {
        this.file = new File(fileName);

        if(file.exists() && !file.canWrite()) {
            throw new IOException("Can't write in this file" + fileName);
        }
    }
}
