package fr.mc2d.gamelauncher.util.classpath;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class ClasspathConstructor extends FileList {

    public ClasspathConstructor() {
        super();
    }

    public ClasspathConstructor(List<Path> classPath) {
        super(classPath);
    }

    public String make() {
        final StringBuilder classPath = new StringBuilder();

        for (int i = 0; i < this.files.size(); i++)
             classPath.append(files.get(i).toString()).append(i + 1 == files.size() ? "" : File.pathSeparator);

        return classPath.toString();
    }
}