package fr.mc2d.gamelauncher.util.exception;

public class LaunchException extends Exception {

    public LaunchException(String message) {
        super(message);
    }

    public LaunchException(String message, Throwable cause) {
        super(message, cause);
    }

    public LaunchException(Throwable cause) {
        super(cause);
    }
}