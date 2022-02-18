package com.example.yeczane.service;

import com.example.yeczane.model.Order;
import com.example.yeczane.model.OrderDetails;

import java.util.List;

public interface OrderService {
    Order addNewOrder(OrderDetails orderDetails, Long userId);
    List<Order> getAllTemporaryOrderByUserId(Long userId);
    List<Order> getAllOrdersByUserId(Long userId);
}
