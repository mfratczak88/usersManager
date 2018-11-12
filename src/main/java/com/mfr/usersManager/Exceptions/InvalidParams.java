package com.mfr.usersManager.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParams extends Exception {
    public InvalidParams(){
        super("Invalid request parameters passed");
    }

}
