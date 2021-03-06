package ruan.eloy.backend.dto;

public class SignInResponse {

    private String accessToken;

    private static String tokenType = "Bearer";

    private static int expiresIn = 18000;

    public SignInResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}
