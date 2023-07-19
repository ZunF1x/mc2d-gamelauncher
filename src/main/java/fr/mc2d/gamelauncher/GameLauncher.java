package fr.mc2d.gamelauncher;

import fr.mc2d.gamelauncher.infos.AuthInfo;
import fr.mc2d.gamelauncher.infos.GameFolder;
import fr.mc2d.gamelauncher.infos.GameInfo;
import fr.mc2d.gamelauncher.util.exception.FolderException;
import fr.mc2d.gamelauncher.util.JavaUtil;
import fr.mc2d.gamelauncher.util.exception.LaunchException;
import fr.mc2d.gamelauncher.util.ProcessLogManager;
import fr.mc2d.gamelauncher.util.classpath.ClasspathConstructor;
import fr.mc2d.gamelauncher.util.explorer.Explorer;
import fr.mc2d.gamelauncher.util.logger.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GameLauncher {

    private final GameInfo gameInfo;
    private final GameFolder gameFolder;
    private final AuthInfo authInfo;

    private final List<String> vmArgs;

    private final LaunchProfile profile;

    public GameLauncher(GameInfo gameInfo, GameFolder gameFolder, AuthInfo authInfo) throws LaunchException {
        this.gameInfo = gameInfo;
        this.gameFolder = gameFolder;
        this.authInfo = authInfo;
        this.vmArgs = new ArrayList<>();

        this.profile = LaunchProfile.makeLaunchProfile(gameInfo, gameFolder, authInfo);
    }

    public Process launch() throws LaunchException {
        ProcessBuilder builder = new ProcessBuilder();

        vmArgs.add(JavaUtil.getJavaCommand());

        if (profile.getMacDockName() != null && System.getProperty("os.name").toLowerCase().contains("mac"))
            vmArgs.add(JavaUtil.macDockName(profile.getMacDockName()));
        if (profile.getVmArgs() != null)
            vmArgs.addAll(profile.getVmArgs());

        vmArgs.add("-cp");
        vmArgs.add(profile.getClassPath());

        vmArgs.add(profile.getMainClass());

        if (profile.getArgs() != null)
            vmArgs.addAll(profile.getArgs());

        if (profile.getDirectory() != null)
            builder.directory(profile.getDirectory().toFile());

        if (profile.isRedirectErrorStream())
            builder.redirectErrorStream(true);

        builder.command(vmArgs);

        final StringBuilder entireCommand = new StringBuilder();
        for (String command : vmArgs)
            entireCommand.append(command).append(" ");

        Logger.LOGGER.debug("ent : " + entireCommand);
        Logger.LOGGER.debug("start " + profile.getMainClass());

        try {
            Process p = builder.start();

            ProcessLogManager manager = new ProcessLogManager(p.getInputStream());
            manager.start();

            return p;
        } catch (IOException e) {
            throw new LaunchException("Error when launching : ", e);
        }
    }

    private static class LaunchProfile {

        private final String mainClass;
        private final String classPath;
        private final List<String> vmArgs;
        private final List<String> args;
        private final boolean redirectErrorStream;
        private final String macDockName;
        private final Path directory;

        public LaunchProfile(String mainClass, String classPath, List<String> vmArgs, List<String> args, boolean redirectErrorStream, String macDockName, Path directory) {
            this.mainClass = mainClass;
            this.classPath = classPath;
            this.vmArgs = vmArgs;
            this.args = args;
            this.redirectErrorStream = redirectErrorStream;
            this.macDockName = macDockName;
            this.directory = directory;
        }

        public String getMainClass() {
            return this.mainClass;
        }

        public String getClassPath() {
            return this.classPath;
        }

        public List<String> getVmArgs() {
            return this.vmArgs;
        }

        public List<String> getArgs() {
            return this.args;
        }

        public boolean isRedirectErrorStream() {
            return this.redirectErrorStream;
        }

        public String getMacDockName() {
            return this.macDockName;
        }

        public Path getDirectory() {
            return this.directory;
        }

        private static LaunchProfile makeLaunchProfile(GameInfo info, GameFolder folder, AuthInfo authInfo) throws LaunchException {
            if (authInfo == null) throw new IllegalArgumentException("authInfos == null");

            checkFolder(folder, info.getGameDir());

            ClasspathConstructor constructor = new ClasspathConstructor();
            List<Path> libs = Explorer.dir(info.getGameDir()).sub(folder.getLibrariesFolder()).allRecursive().files().match("^(.*\\.((jar)$))*$").get();

            constructor.add(libs);
            constructor.add(Explorer.dir(info.getGameDir()).get(folder.getClient()));

            String mainClass = info.getGameVersion().getGameType().getMainClass(info);
            String classpath = constructor.make();
            List<String> args = info.getGameVersion().getGameType().getLaunchArgs(info, folder, authInfo);
            List<String> vmArgs = new ArrayList<>();

            String nativesFolder = folder.getNativesFolder();
            vmArgs.add("-Djava.library.path=" + (nativesFolder.equals(".") ? "." : Explorer.dir(info.getGameDir()).sub(nativesFolder).get().toString()));

            return new LaunchProfile(mainClass, classpath, vmArgs, args, true, info.getServerName(), info.getGameDir());
        }

        private static void checkFolder(GameFolder folder, Path directory) throws FolderException {
            Path libsFolder = directory.resolve(folder.getLibrariesFolder());
            Path nativesFolder = directory.resolve(folder.getNativesFolder());
            Path minecraftJar = directory.resolve(folder.getClient());

            try {
                if (Files.notExists(libsFolder) || notEmpty(libsFolder))
                    throw new FolderException("Missing/Empty libraries folder (" + libsFolder + ")");
                else if (Files.notExists(nativesFolder) || notEmpty(nativesFolder))
                    throw new FolderException("Missing/Empty natives folder (" + nativesFolder + ")");
                else if (Files.notExists(minecraftJar))
                    throw new FolderException("Missing main jar (" + minecraftJar + ")");
            } catch (IOException e) {
                throw new FolderException(e);
            }
        }

        private static boolean notEmpty(Path path) throws IOException {
            try(final Stream<Path> children = Files.list(path)) {
                return children == null || !children.findAny().isPresent();
            }
        }
    }
}
