package fr.mc2d.gamelauncher.infos.version;

import fr.mc2d.gamelauncher.infos.AuthInfo;
import fr.mc2d.gamelauncher.infos.GameFolder;
import fr.mc2d.gamelauncher.infos.GameInfo;

import java.util.List;

public abstract class GameType {

    public static final GameType MC2D = new GameType() {
        @Override
        public String getName() {
            return "MC2D";
        }

        @Override
        public String getMainClass(GameInfo gameInfo) {
            return "fr.mc2d.client.main.Main";
        }

        @Override
        public List<String> getLaunchArgs(GameInfo info, GameFolder folder, AuthInfo authInfo) {
            return null;
        }
    };

    public abstract String getName();
    public abstract String getMainClass(GameInfo gameInfo);
    public abstract List<String> getLaunchArgs(GameInfo info, GameFolder folder, AuthInfo authInfo);
}
