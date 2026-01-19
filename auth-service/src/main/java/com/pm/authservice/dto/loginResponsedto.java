package com.pm.authservice.dto;

public class loginResponsedto {

    private final String token;

    public loginResponsedto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
