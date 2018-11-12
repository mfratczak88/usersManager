package com.mfr.usersManager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
