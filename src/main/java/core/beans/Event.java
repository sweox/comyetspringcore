package core.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by BELSHINA on 28.11.2017.
 */

@Component
@Scope("prototype")
public class Event {
//    private int id = (int)(Math.random()*77);
    private int id;
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private String msg;


    @Value("#{new java.util.Date()}")
    private Date date;

    @Value("#{T(java.text.DateFormat).getDateTimeInstance()}")
    private DateFormat dateFormat;

    public static boolean isDay(int start, int end) {
        LocalTime time = LocalTime.now();
        return time.getHour() > start && time.getHour() < end;
    }

    public Event() {
        this.id = AUTO_ID.getAndIncrement();
    }

    public Event(Date date, DateFormat dateFormat) {
        this.id = AUTO_ID.getAndIncrement();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + dateFormat.format(date) +
                '}';
    }
}
