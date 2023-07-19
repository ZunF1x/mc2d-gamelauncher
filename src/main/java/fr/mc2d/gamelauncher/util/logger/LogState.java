package fr.mc2d.gamelauncher.util.logger;

public enum LogState {

    INFO("info"),
    WARN("warning"),
    ERROR("error"),
    DEBUG("debug");

    private final String title;

    LogState(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
