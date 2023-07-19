package fr.mc2d.gamelauncher.infos.version;

public class GameVersion {

    private final String versionName;
    private final GameType gameType;

    public GameVersion(String versionName, GameType gameType) {
        this.versionName = versionName;
        this.gameType = gameType;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public GameType getGameType() {
        return this.gameType;
    }
}
