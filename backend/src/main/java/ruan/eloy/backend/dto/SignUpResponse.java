package ruan.eloy.backend.dto;

public class SignUpResponse {

    private Boolean success;

    private String message;

    private String registration;

    public SignUpResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SignUpResponse(Boolean success, String message, String registration) {
        this.success = success;
        this.message = message;
        this.registration = registration;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
