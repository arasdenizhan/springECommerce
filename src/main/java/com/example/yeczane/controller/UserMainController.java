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

import java.security.Principal;
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

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal){
        String currentUsersUsername = principal.getName();
        Users tempUser = userService.getUserByUsername(currentUsersUsername);
        UsersDto userDto = new UsersDto(
                tempUser.getId(),
                tempUser.getUsername(),
                tempUser.getPassword(),
                tempUser.getPassword()
        );
        model.addAttribute("currentUser", userDto);
        return "profile";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("register")
    public String getRegisterPage(Model model){
        model.addAttribute("usersDto", new UsersDto());
        return "register";
    }

    @PostMapping("register")
    public String addNewUser(@ModelAttribute("usersDto") UsersDto userDto)
    {
        Objects.requireNonNull(userDto);
        if(userService.addNewUser(userDto)!=null){
            return "success";
        }
        return "error";
    }

    @PostMapping("profile")
    public String updateUser(@ModelAttribute("currentUser") UsersDto userDto)
    {
        Objects.requireNonNull(userDto);
        if(Boolean.TRUE.equals(userService.updateUser(userDto))){
            return "redirect:/logout";
        }
        return "error";
    }
}
