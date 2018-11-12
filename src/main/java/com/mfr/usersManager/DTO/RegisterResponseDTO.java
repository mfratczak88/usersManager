package com.mfr.usersManager.DTO;


public class RegisterResponseDTO {
    private String id;
    private  String message = "User created";



    public RegisterResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
