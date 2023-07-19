package fr.mc2d.gamelauncher.util.logger;

public interface ILogger {

    void info(String message);
    void info(String... messages);
    void info(String message, Throwable t);

    void warn(String message);
    void warn(String... messages);
    void warn(String message, Throwable t);

    void err(String message);
    void err(String... messages);
    void err(String message, Throwable t);

    void debug(String message);
    void debug(String... messages);
    void debug(String message, Throwable t);

    void setDebug(boolean debug);
    boolean isDebug();

    default void print(LogState state, String message) {
        System.out.println(message);
    }

    default void print(LogState state, String... messages) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < messages.length; i++) {
            message.append(messages[i]);
            if (i == messages.length - 1) message.append(" ");
        }

        System.out.println(message);
    }

    default void print(LogState state, String message, Throwable t) {
        System.out.println(message + ": " + t.getMessage());
    }
}
