package com.example.yeczane.service;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.model.Order;

import java.util.List;

public interface OrderService {
    Order addNewOrder(OrderDetailsDto orderDetailsDto, Long userId);
    Order getTemporaryOrderByUserId(Long userId);
    List<Order> getAllOrdersByUserId(Long userId);
}
