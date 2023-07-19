package fr.mc2d.gamelauncher.util.explorer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Explorer extends ExploredDirectory {

    public Explorer(Path directory) {
        super(directory);
    }

    public void cd(Path cd) {
        this.directory = cd;
    }

    public void cd(String cd) {
        this.directory = FilesUtil.get(directory, cd);
    }

    public static ExploredDirectory dir(String dir) {
        return dir(Paths.get(dir));
    }

    public static ExploredDirectory dir(Path dir) {
        return new ExploredDirectory(FilesUtil.dir(dir));
    }
}