package fr.mc2d.gamelauncher.infos;

public class AuthInfo {

    private final String username;
    private final String accessToken;
    private final String uuid;

    public AuthInfo(String username, String accessToken, String uuid) {
        this.username = username;
        this.accessToken = accessToken;
        this.uuid = uuid;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getUUID() {
        return this.uuid;
    }
}
