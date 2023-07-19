package fr.mc2d.gamelauncher.util;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class JavaUtil {

    private static String javaCommand;

    public static String macDockName(String name) {
        return "-Xdock:name=" + name;
    }

    public static String getJavaCommand() {
        if (javaCommand == null) {
            Path java = Paths.get(System.getProperty("java.home"), "bin", "java");
            if (Objects.requireNonNull(System.getProperty("os.name")).toLowerCase().contains("win")) javaCommand = "\"" + java + "\"";
            else javaCommand = java.toString();
        }
        return javaCommand;
    }

    public static void setJavaCommand(String javaCommandPath) {
        javaCommand = javaCommandPath;
    }

    public static void setLibraryPath(String path) throws Exception {
        System.setProperty("java.library.path", path);

        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
    }
}