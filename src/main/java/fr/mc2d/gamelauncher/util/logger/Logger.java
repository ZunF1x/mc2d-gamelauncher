package fr.mc2d.gamelauncher.util.logger;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements ILogger {

    public static final ILogger LOGGER = new Logger();

    private static final Format hour = new SimpleDateFormat("HH:mm:ss");
    private static final Date date = new Date();

    private boolean debug;

    private Logger() {
        this.debug = false;
    }

    @Override
    public void info(String message) {
        this.print(LogState.INFO, message);
    }

    @Override
    public void info(String... messages) {
        this.print(LogState.INFO, messages);
    }

    @Override
    public void info(String message, Throwable t) {
        this.print(LogState.INFO, message, t);
    }

    @Override
    public void warn(String message) {
        this.print(LogState.WARN, message);
    }

    @Override
    public void warn(String... messages) {
        this.print(LogState.WARN, messages);
    }

    @Override
    public void warn(String message, Throwable t) {
        this.print(LogState.WARN, message, t);
    }

    @Override
    public void err(String message) {
        this.print(LogState.ERROR, message);
    }

    @Override
    public void err(String... messages) {
        this.print(LogState.ERROR, messages);
    }

    @Override
    public void err(String message, Throwable t) {
        this.print(LogState.ERROR, message, t);
    }

    @Override
    public void debug(String message) {
        if (this.debug) this.print(LogState.DEBUG, message);
    }

    @Override
    public void debug(String... messages) {
        if (this.debug) this.print(LogState.DEBUG, messages);
    }

    @Override
    public void debug(String message, Throwable t) {
        if (this.debug) this.print(LogState.DEBUG, message, t);
    }

    @Override
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public boolean isDebug() {
        return this.debug;
    }

    @Override
    public void print(LogState state, String message) {
        System.out.println("[" + hour.format(date) + "] [" + Thread.currentThread().getName() + "/" + state.getTitle() + "]: " + message);
    }

    @Override
    public void print(LogState state, String... messages) {
        StringBuilder message = new StringBuilder();
        message.append("[").append(hour.format(date)).append("] [").append(Thread.currentThread().getName()).append("/").append(state.getTitle()).append("]: ");

        for (int i = 0; i < messages.length; i++) {
            message.append(messages[i]);
            if (i == messages.length - 1) message.append(" ");
        }

        System.out.println(message);
    }

    @Override
    public void print(LogState state, String message, Throwable t) {
        System.out.println("[" + hour.format(date) + "] [" + Thread.currentThread().getName() + "/" + state.getTitle() + "]: " + message + "> " + t.getMessage());
    }
}
