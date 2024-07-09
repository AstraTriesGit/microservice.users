package io.gigachad.microservice.users.exceptions;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException() {
        super();
    }

    public InvalidPhoneNumberException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writeableStackTrace) {
        super(message, cause, enableSuppression, writeableStackTrace);
    }

    public InvalidPhoneNumberException(String message) {
        super(message);
    }

    public InvalidPhoneNumberException(Throwable cause) {
        super(cause);
    }
}
