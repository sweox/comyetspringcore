package core.aspects;

import core.beans.Event;
import core.loggers.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ConsoleLoggerLimitAspect {

    private final int maxCount;

    private final EventLogger otherLogger;

    private int currentCount = 0;

    @Autowired
    public ConsoleLoggerLimitAspect(
            @Value("${console.logger.max:2}") int maxCount, 
            @Qualifier("fileEventLogger") EventLogger otherLogger) {
        this.maxCount = maxCount;
        this.otherLogger = otherLogger;
    }

    @Around("execution(* *.logEvent(core.beans.Event)) "
            + "&& within(core.loggers.ConsoleEventLogger) "
            + "&& args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Event evt) throws Throwable {
        if (currentCount < maxCount) {
            System.out.println("ConsoleEventLogger max count is not reached. Continue...");
            currentCount++;
            jp.proceed(new Object[] { evt });
        } else {
            System.out.println("ConsoleEventLogger max count is reached. Logging to " + otherLogger.getName());
            otherLogger.logEvent(evt);
        }
    }

}