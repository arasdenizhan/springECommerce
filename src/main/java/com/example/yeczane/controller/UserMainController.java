package com.example.yeczane.controller;

import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.model.Users;
import com.example.yeczane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UserMainController {

    private final UserService userService;

    @Autowired
    public UserMainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = {"/","/home"})
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("register")
    public String registerPage(Model model){
        model.addAttribute("usersDto", new UsersDto());
        return "register";
    }

    @PostMapping("register")
    public String addNewUser(@ModelAttribute("usersDto") UsersDto userDto)
    {
        Objects.requireNonNull(userDto);
        userService.addNewUser(userDto);
        return "success";
    }
}
