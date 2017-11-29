import beans.Client;
import beans.Event;
import loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by BELSHINA on 28.11.2017.
 */
public class App {
    Client client;
    EventLogger eventLogger;

    public static void main(String[] args) {
        @SuppressWarnings("resource") // We will remove this suppress in further lessons
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
