package com.example.yeczane.service;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.model.OrderDetails;

public interface OrderDetailsService {
    OrderDetails updateOrderDetails(OrderDetailsDto orderDetailsDto);
}
