package com.example.yeczane.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDto {
    public Long id;
    public String username;
    public String password;
    public String email;
    public String customerName = "Undefined";
    public String customerAddress = "Undefined";
    public String customerPhone = "Undefined";

    public UsersDto(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
