package fr.mc2d.gamelauncher.util.explorer;

import fr.mc2d.gamelauncher.util.classpath.FileList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExploredDirectory {

    protected Path directory;

    public ExploredDirectory(Path directory) {
        this.directory = directory;
    }

    public FileList allRecursive() {
        return new FileList(FilesUtil.listRecursive(this.directory));
    }

    public FileList list() {
        return new FileList(FilesUtil.list(this.directory));
    }

    public ExploredDirectory sub(String directory) {
        return new ExploredDirectory(FilesUtil.dir(this.directory, directory));
    }

    public Path get(String file) {
        return FilesUtil.get(this.directory, file);
    }

    public FileList subs() {
        final List<Path> files = FilesUtil.list(this.directory);
        final List<Path> dirs  = new ArrayList<>();

        for (Path f : files)
            if (Files.isDirectory(f))
                dirs.add(f);

        return new FileList(dirs);
    }

    public FileList files() {
        List<Path> files = FilesUtil.list(this.directory);
        List<Path> fs = new ArrayList<>();

        for (Path f : files)
            if (!Files.isDirectory(f)) fs.add(f);

        return new FileList(fs);
    }

    public Path get() {
        return this.directory;
    }
}