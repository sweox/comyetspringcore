package loggers;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by BELSHINA on 28.11.2017.
 */
public class Event {
    private int id = (int)(Math.random()*77);
    private String msg;
    private Date date;
    private DateFormat df;

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
