package com.example.yeczane.service;

import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.model.Users;

public interface UserService {
    Users addNewUser(UsersDto usersDto);
    Users addNewAdminUser(UsersDto usersDto);
    Users getUserByUsername(String username);
}
