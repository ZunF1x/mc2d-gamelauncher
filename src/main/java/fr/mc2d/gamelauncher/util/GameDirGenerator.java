package fr.mc2d.gamelauncher.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class GameDirGenerator {

    public static Path createGameDir(String serverName, boolean inLinuxLocalShare) {
        String os = Objects.requireNonNull(System.getProperty("os.name")).toLowerCase();
        if (os.contains("win")) return Paths.get(System.getenv("APPDATA"), '.' + serverName);
        else if (os.contains("mac")) return Paths.get(System.getProperty("user.home"), "Library", "Application Support", serverName);
        else {
            if (inLinuxLocalShare && os.contains("linux")) return Paths.get(System.getProperty("user.home"), ".local", "share", serverName);
            else return Paths.get(System.getProperty("user.home"), '.' + serverName);
        }
    }
}
