package com.example.yeczane.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    private String username;
    private String password;
    private String email;

    public UsersDto() {
    }

    public UsersDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
