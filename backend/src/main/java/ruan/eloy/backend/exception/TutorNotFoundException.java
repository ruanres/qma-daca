package ruan.eloy.backend.exception;

public class TutorNotFoundException extends RuntimeException {

    public TutorNotFoundException(String message) {
        super("Tutor not found. " + message);
    }

    public TutorNotFoundException() {
        super("Tutor not found");
    }
}
