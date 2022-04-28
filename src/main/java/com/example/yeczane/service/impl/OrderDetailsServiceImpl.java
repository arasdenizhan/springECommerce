package com.example.yeczane.service.impl;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.model.OrderDetails;
import com.example.yeczane.repository.OrderDetailsRepository;
import com.example.yeczane.service.OrderDetailsService;
import com.example.yeczane.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderService orderService;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository, OrderService orderService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderService = orderService;
    }

    @Override
    public OrderDetails updateOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetailsByProductCode = orderDetailsRepository.getOrderDetailsByProductCode(orderDetailsDto.getProduct().getCode());
        Double amount = Double.valueOf(orderDetailsDto.getAmount());
        if(orderDetailsByProductCode.getAmount()>1){
            orderDetailsByProductCode.setPrice(orderDetailsByProductCode.getPrice()/orderDetailsByProductCode.getAmount()*amount);
        } else {
            orderDetailsByProductCode.setPrice(orderDetailsByProductCode.getPrice()*amount);
        }
        orderDetailsByProductCode.setAmount(amount);
        return orderDetailsRepository.save(orderDetailsByProductCode);
    }
}
