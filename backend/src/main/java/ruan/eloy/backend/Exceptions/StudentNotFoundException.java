package ruan.eloy.backend.Exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class StudentNotFoundException extends UsernameNotFoundException {

    public StudentNotFoundException(String msg) {
        super("Student not found. " + msg);
    }

    public StudentNotFoundException() {
        super("Student not found");
    }

    public StudentNotFoundException(String msg, Throwable t) {
        super("Student not found. " + msg, t);
    }
}
