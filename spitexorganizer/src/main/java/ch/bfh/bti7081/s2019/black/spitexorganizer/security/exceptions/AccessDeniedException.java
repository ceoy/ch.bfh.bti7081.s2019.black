package ch.bfh.bti7081.s2019.black.spitexorganizer.security.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
