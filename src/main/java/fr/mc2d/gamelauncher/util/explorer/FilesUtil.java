package fr.mc2d.gamelauncher.util.explorer;

import fr.mc2d.gamelauncher.util.exception.FailException;
import fr.mc2d.gamelauncher.util.exception.FolderException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesUtil {

    public static List<Path> listRecursive(Path directory) {
        final List<Path> files = new ArrayList<>();
        final List<Path> fs = list(directory);

        for (final Path f : fs) {
            if (Files.isDirectory(f)) files.addAll(listRecursive(f));
            files.add(f);
        }

        return files;
    }

    public static Path get(Path root, String file) {
        final Path f = root.resolve(file);
        if (Files.notExists(f)) throw new FailException("Given file/directory doesn't exist !");

        return f;
    }

    public static Path dir(Path d) {
        if (!Files.isDirectory(d))
            throw new FailException("Given directory is not one !");

        return d;
    }

    public static Path dir(Path root, String dir)
    {
        return dir(get(root, dir));
    }

    public static List<Path> list(final Path dir) {
        List<Path> result = new ArrayList<>();

        try {
            if(Files.exists(dir)) {
                try (final Stream<Path> stream = Files.list(dir)) {
                    result.addAll(stream.collect(Collectors.toList()));
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailException(e.getMessage());
        }
    }
}