package fr.mc2d.gamelauncher.infos;

public class GameFolder {

    private final String libraries;
    private final String natives;
    private final String client;

    public GameFolder(String libraries, String natives, String client) {
        this.libraries = libraries;
        this.natives = natives;
        this.client = client;
    }

    public String getLibrariesFolder() {
        return this.libraries;
    }

    public String getNativesFolder() {
        return this.natives;
    }

    public String getClient() {
        return this.client;
    }

    public static GameFolder makeGameFolder(String libraries, String natives, String client) {
        return new GameFolder(libraries, natives, client);
    }
}
