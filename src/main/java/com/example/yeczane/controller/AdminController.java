package com.example.yeczane.controller;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.model.Product;
import com.example.yeczane.model.Users;
import com.example.yeczane.service.ProductService;
import com.example.yeczane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class AdminController {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AdminController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/product")
    public String createProduct(@ModelAttribute("productDto") ProductDto productDto){
        Objects.requireNonNull(productDto, "Product cannot be null.");
        Product product = productService.addNewProduct(productDto);
        if(Objects.nonNull(product)){
            return "admin-success";
        }
        return "error";
    }

    @PostMapping("/registerAdmin")
    public String registerNewAdmin(@ModelAttribute("usersDto") UsersDto usersDto){
        Objects.requireNonNull(usersDto);
        Users user = userService.addNewAdminUser(usersDto);
        if(Objects.nonNull(user)){
            return "admin-success";
        }
        return "error";
    }
}
