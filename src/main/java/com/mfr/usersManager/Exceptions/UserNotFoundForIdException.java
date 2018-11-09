package com.mfr.usersManager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundForIdException extends Exception {

    public UserNotFoundForIdException(String id){
        super("User with id " + id + " not found");
    }
}
