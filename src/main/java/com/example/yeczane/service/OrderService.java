package com.example.yeczane.service;

import com.example.yeczane.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrdersByUserId(Long userId);
}
