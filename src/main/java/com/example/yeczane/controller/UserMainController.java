package com.example.yeczane.controller;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.model.CustomerInfo;
import com.example.yeczane.model.Users;
import com.example.yeczane.service.CustomerInfoService;
import com.example.yeczane.service.ProductService;
import com.example.yeczane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductService productService;
    private final CustomerInfoService customerInfoService;

    @Autowired
    public UserMainController(UserService userService, ProductService productService, CustomerInfoService customerInfoService) {
        this.userService = userService;
        this.productService = productService;
        this.customerInfoService = customerInfoService;
    }

    @GetMapping(path = {"","/"})
    public String getRootPage(){
        return "redirect:userHome";
    }

    @GetMapping("/userHome")
    public String getHomePage(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "userHome";
    }

    @GetMapping("/userProfile")
    public String getProfilePage(Model model, Principal principal){
        String currentUsersUsername = principal.getName();
        Users tempUser = userService.getUserByUsername(currentUsersUsername);
        CustomerInfo customerInfo = customerInfoService.findCustomerInfoByUserId(tempUser.getId());
        UsersDto userDto = new UsersDto(
                tempUser.getId(),
                tempUser.getUsername(),
                tempUser.getPassword(),
                tempUser.getEmail()
        );
        if(customerInfo!=null){
            userDto.setCustomerName(customerInfo.getCustomerName());
            userDto.setCustomerAddress(customerInfo.getCustomerAddress());
            userDto.setCustomerPhone(customerInfo.getCustomerPhone());
        }
        model.addAttribute("currentUser", userDto);
        return "userProfile";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/loginAdmin")
    public String getLoginAdmin(){
        return "loginAdmin";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("usersDto", new UsersDto());
        return "register";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("productDto", new ProductDto());
        return "admin";
    }

    @GetMapping("adminRegister")
    public String getAdminRegisterPage(){
        return "adminRegister";
    }

    @PostMapping("/register")
    public String addNewUser(@ModelAttribute("usersDto") UsersDto userDto)
    {
        Objects.requireNonNull(userDto);
        if(userService.addNewUser(userDto)!=null){
            return "success";
        }
        return "error";
    }

    @PostMapping("/userProfile")
    public String updateUser(@ModelAttribute("currentUser") UsersDto userDto)
    {
        Objects.requireNonNull(userDto);
        if(Boolean.TRUE.equals(userService.updateUser(userDto))){
            return "redirect:/logout";
        }
        return "error";
    }
}
