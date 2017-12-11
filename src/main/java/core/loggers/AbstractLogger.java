package core.loggers;

/**
 * Created by BELSHINA on 11.12.2017.
 */
public abstract class AbstractLogger implements EventLogger {

    protected String name;

    @Override
    public String getName() {
        return name;
    }

    protected abstract void setName(String name);
}
