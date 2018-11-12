package com.mfr.usersManager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EmailOrPasswordMismatchException extends Exception {
    public EmailOrPasswordMismatchException() {
        super("Email or Password Mismatch");
    }
}
