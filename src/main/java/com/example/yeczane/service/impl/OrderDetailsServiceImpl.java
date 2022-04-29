package com.example.yeczane.service.impl;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.model.OrderDetails;
import com.example.yeczane.repository.OrderDetailsRepository;
import com.example.yeczane.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public OrderDetails updateOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetailsByProductCode = orderDetailsRepository.getOrderDetailsById(orderDetailsDto.getId());
        Double amount = Double.valueOf(orderDetailsDto.getAmount());
        if(orderDetailsByProductCode.getAmount()>1){
            orderDetailsByProductCode.setPrice(orderDetailsByProductCode.getPrice()/orderDetailsByProductCode.getAmount()*amount);
        } else {
            orderDetailsByProductCode.setPrice(orderDetailsByProductCode.getPrice()*amount);
        }
        orderDetailsByProductCode.setAmount(amount);
        return orderDetailsRepository.save(orderDetailsByProductCode);
    }

    @Override
    public boolean deleteOrderDetailById(Long orderDetailId) {
        if(orderDetailsRepository.existsById(orderDetailId)){
            orderDetailsRepository.deleteOrderDetailsById(orderDetailId);
            return true;
        }
        return false;
    }
}
