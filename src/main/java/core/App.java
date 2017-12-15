package core;

import core.aspects.StatisticsAspect;
import core.beans.Client;
import core.beans.Event;
import core.beans.EventType;
import core.loggers.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import core.spring.AppConfig;
import core.spring.LoggerConfig;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by BELSHINA on 28.11.2017.
 */

@Service
public class App {
    @Autowired
    private Client client;

    @Value("#{ T(core.beans.Event).isDay(8,17) ? "
            + "cacheFileEventLogger : consoleEventLogger }")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    @Value("#{'Hello user ' + "
            + "( systemProperties['os.name'].contains('Windows') ? "
            + "systemEnvironment['USERNAME'] : systemEnvironment['USER'] ) + "
            + "'. Default logger is ' + app.defaultLogger.name }")
    private String startupMessage;

    @Autowired
    private StatisticsAspect statisticsAspect;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("core");
        ctx.refresh();

        App app = (App)ctx.getBean("app");

        System.out.println(app.startupMessage);

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for user 1");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 3");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 4");

        app.outputLoggingCounter();

        ctx.close();
    }

    public App() {}
    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

    private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.println("Loggers statistics. Number of calls: ");
            for (Map.Entry<Class<?>, Integer> entry: statisticsAspect.getCounter().entrySet()) {
                System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
            }
        }
    }
}
