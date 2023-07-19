package fr.mc2d.gamelauncher.infos;

import fr.mc2d.gamelauncher.infos.version.GameVersion;
import fr.mc2d.gamelauncher.util.GameDirGenerator;

import java.nio.file.Path;

public class GameInfo {

    private final String serverName;
    private final Path gameDir;
    private final GameVersion gameVersion;

    public GameInfo(String serverName, GameVersion gameVersion) {
        this(serverName, GameDirGenerator.createGameDir(serverName, true), gameVersion);
    }

    public GameInfo(String serverName, Path gameDir, GameVersion gameVersion) {
        this.serverName = serverName;
        this.gameDir = gameDir;
        this.gameVersion = gameVersion;
    }

    public String getServerName() {
        return this.serverName;
    }

    public Path getGameDir() {
        return this.gameDir;
    }

    public GameVersion getGameVersion() {
        return this.gameVersion;
    }
}
