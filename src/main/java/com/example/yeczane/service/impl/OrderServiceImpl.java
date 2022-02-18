package com.example.yeczane.service.impl;

import com.example.yeczane.model.Order;
import com.example.yeczane.model.OrderDetails;
import com.example.yeczane.model.OrderStatus;
import com.example.yeczane.repository.OrderRepository;
import com.example.yeczane.service.CustomerInfoService;
import com.example.yeczane.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerInfoService customerInfoService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerInfoService customerInfoService) {
        this.orderRepository = orderRepository;
        this.customerInfoService = customerInfoService;
    }

    @Override
    public Order addNewOrder(OrderDetails orderDetails, Long userId) {
        List<Order> temporaryOrders = getAllTemporaryOrderByUserId(userId);
        if (temporaryOrders.isEmpty()){
            Order newOrder = new Order();
            newOrder.setOrderDate(new Date());
            newOrder.setCustomerInfo(customerInfoService.findCustomerInfoByUserId(userId));
            newOrder.setOrderDetails(List.of(orderDetails));
            orderDetails.setOrder(newOrder);
            return orderRepository.save(newOrder);
        } else {
            Order orderToBeUpdated = temporaryOrders.get(0);
            orderDetails.setOrder(orderToBeUpdated);
            orderToBeUpdated.setOrderDetails(
                    Stream.concat(
                            orderToBeUpdated.getOrderDetails().stream()
                            , Stream.of(orderDetails)
                    ).collect(Collectors.toList()));
            return orderRepository.save(orderToBeUpdated);
        }
    }

    @Override
    public List<Order> getAllTemporaryOrderByUserId(Long userId) {
        return orderRepository.getAllOrdersByUserId(userId)
                .stream()
                .filter(order -> order.getOrderStatus().equals(OrderStatus.TEMPORARY))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderRepository.getAllOrdersByUserId(userId);
    }
}
