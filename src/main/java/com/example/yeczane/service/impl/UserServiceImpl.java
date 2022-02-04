package com.example.yeczane.service.impl;

import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.model.UserRoles;
import com.example.yeczane.model.Users;
import com.example.yeczane.repository.UserRepository;
import com.example.yeczane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users addNewUser(UsersDto usersDto) {
        Users users = new Users();
        if(userRepository.findByUsername(users.getUsername())!=null){
            return null;
        }
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setEmail(usersDto.getEmail());
        return userRepository.save(users);
    }

    @Override
    public Users addNewAdminUser(UsersDto usersDto) {
        Users users = new Users();
        if(userRepository.findByUsername(users.getUsername())!=null){
            return null;
        }
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setEmail(usersDto.getEmail());
        users.setRole(UserRoles.ADMIN);
        return userRepository.save(users);
    }

    @Override
    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean updateUser(UsersDto usersDto) {
        Optional<Users> usersOptional = userRepository.findById(usersDto.getId());
        if(usersOptional.isPresent()){
            Users usersToBeUpdated = usersOptional.get();
            usersToBeUpdated.setUsername(usersDto.getUsername());
            usersToBeUpdated.setEmail(usersDto.getEmail());
            usersToBeUpdated.setPassword(usersDto.getPassword());
            userRepository.save(usersToBeUpdated);
            return true;
        }
        return false;
    }
}
