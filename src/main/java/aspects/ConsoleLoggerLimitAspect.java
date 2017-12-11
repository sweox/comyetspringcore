package aspects;

import beans.Event;
import loggers.EventLogger;
import org.aspectj.lang.ProceedingJoinPoint;

public class ConsoleLoggerLimitAspect {
    
    private final int maxCount;
    
    private final EventLogger otherLogger;
    
    private int currentCount = 0;
    
    public ConsoleLoggerLimitAspect(int maxCount, EventLogger otherLogger) {
        this.maxCount = maxCount;
        this.otherLogger = otherLogger;
    }
    
    public void aroundLogEvent(ProceedingJoinPoint jp, Event evt) throws Throwable {
        if (currentCount < maxCount) {
            System.out.println("ConsoleEventLogger max count is not reached. Continue...");
            currentCount++;
            jp.proceed(new Object[] {evt});
        } else {
            System.out.println("ConsoleEventLogger max count is reached. Logging to " + otherLogger.getName());
            otherLogger.logEvent(evt);
        }
    }

}
