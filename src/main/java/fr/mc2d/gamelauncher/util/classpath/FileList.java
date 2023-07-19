package fr.mc2d.gamelauncher.util.classpath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileList {

    protected List<Path> files;

    public FileList() {
        this.files = new ArrayList<>();
    }

    public FileList(List<Path> files) {
        this.files = files;
    }

    public void add(Path... files) {
        this.add(Arrays.asList(files));
    }

    public void add(List<Path> files) {
        this.files.addAll(files);
    }

    public void add(FileList list) {
        this.add(list.get());
    }

    public FileList match(String regex) {
        List<Path> matching = new ArrayList<>();

        for (Path f : this.files)
            if (f.toString().matches(regex)) matching.add(f);

        return new FileList(matching);
    }

    public FileList dirs() {
        List<Path> dirs = new ArrayList<>();

        for (Path f : this.files)
            if (Files.isDirectory(f)) dirs.add(f);

        return new FileList(dirs);
    }

    public FileList files() {
        List<Path> files = new ArrayList<>();

        for (Path f : this.files)
            if (!Files.isDirectory(f))
                files.add(f);

        return new FileList(files);
    }

    public List<Path> get() {
        return this.files;
    }
}
