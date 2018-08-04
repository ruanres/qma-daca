package ruan.eloy.backend.dto;

import org.springframework.beans.factory.annotation.Value;

public class JwtAuthenticationResponse {

    private String accessToken;

    private String tokenType = "Bearer";

    private int expiresIn = 18000;

    public JwtAuthenticationResponse(String accessToken) {
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
