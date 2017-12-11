package loggers;

/**
 * Created by BELSHINA on 11.12.2017.
 */
public abstract class AbstractLogger implements EventLogger {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
