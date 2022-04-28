package com.example.yeczane.controller;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.dto.OrderDto;
import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.dto.populator.OrderPopulator;
import com.example.yeczane.dto.populator.UserPopulator;
import com.example.yeczane.model.*;
import com.example.yeczane.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class UserMainController {

    private final UserService userService;
    private final ProductService productService;
    private final CustomerInfoService customerInfoService;
    private final OrderService orderService;

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public UserMainController(UserService userService, ProductService productService, CustomerInfoService customerInfoService, OrderService orderService, OrderDetailsService orderDetailsService) {
        this.userService = userService;
        this.productService = productService;
        this.customerInfoService = customerInfoService;
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
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
        UsersDto userDto = UserPopulator.populateDto(tempUser);
        UserPopulator.populateCustomerInfo(userDto, customerInfo);
        model.addAttribute("currentUser", userDto);
        return "userProfile";
    }

    @GetMapping("/userOrderList")
    public String getOrderList(Model model, Principal principal){
        String currentUsersUsername = principal.getName();
        Users tempUser = userService.getUserByUsername(currentUsersUsername);
        List<Order> allOrdersByUserId = orderService.getAllOrdersByUserId(tempUser.getId());
        List<OrderDto> orderDtoList = new ArrayList<>();
        allOrdersByUserId.stream()
                .filter(order -> ObjectUtils.notEqual(order.getOrderStatus(),OrderStatus.TEMPORARY))
                .forEach(order -> orderDtoList.add(OrderPopulator.populateOrderDto(order)));
        model.addAttribute("orderDtoList", orderDtoList);
        return "userOrderList";
    }

    @GetMapping("/userShoppingCart")
    public String getShoppingCart(Model model, Principal principal){
        String currentUsersUsername = principal.getName();
        Users tempUser = userService.getUserByUsername(currentUsersUsername);
        List<Order> allOrdersByUserId = orderService.getAllTemporaryOrderByUserId(tempUser.getId());
        List<OrderDto> orderDtoList = new ArrayList<>();
        allOrdersByUserId.forEach(order -> orderDtoList.add(OrderPopulator.populateOrderDto(order)));
        model.addAttribute("orderDtoList", orderDtoList);
        model.addAttribute("updateOrderDetailsDto", new OrderDetailsDto());
        return "userShoppingCart";
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

    @PostMapping("/updateOrderDetails")
    public String updateOrderDetails(@ModelAttribute("updateOrderDetailsDto") OrderDetailsDto updateOrderDetailsDto){
        Objects.requireNonNull(updateOrderDetailsDto);
        if(orderDetailsService.updateOrderDetails(updateOrderDetailsDto)!=null){
            return "redirect:/userShoppingCart";
        }
        return "error";
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
