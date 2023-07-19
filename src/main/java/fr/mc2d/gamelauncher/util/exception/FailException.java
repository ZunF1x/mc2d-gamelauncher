package fr.mc2d.gamelauncher.util.exception;

public class FailException extends RuntimeException {

    public FailException(String message) {
        super(message);
    }

    public FailException(String message, Throwable cause) {
        super(message, cause);
    }
}
