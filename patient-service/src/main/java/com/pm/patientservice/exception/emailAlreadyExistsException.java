package com.pm.patientservice.exception;

public class emailAlreadyExistsException extends RuntimeException {

    public emailAlreadyExistsException(String message)
    {
        super(message);
    }
}
