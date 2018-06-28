package ruan.eloy.backend.Exceptions;


public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String msg) {
        super("Student not found. " + msg);
    }

    public StudentNotFoundException() {
        super("Student not found");
    }

}
