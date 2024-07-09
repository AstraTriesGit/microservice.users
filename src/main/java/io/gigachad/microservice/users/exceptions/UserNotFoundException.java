package io.gigachad.microservice.users.exceptions;

/**
 * This exception is thrown when a request for a user's PAN info who does not exist
 * in the database is requested. It overrides all 4 constructors of RuntimeException.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writeableStackTrace) {
        super(message, cause, enableSuppression, writeableStackTrace);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}

