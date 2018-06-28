package ruan.eloy.backend.dto;

import javax.validation.constraints.NotBlank;

public class SignInRequest {

    @NotBlank
    private String registration;

    @NotBlank
    private String password;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
