package com.example.yeczane.service.impl;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.model.Order;
import com.example.yeczane.model.OrderDetails;
import com.example.yeczane.repository.OrderRepository;
import com.example.yeczane.service.CustomerInfoService;
import com.example.yeczane.service.OrderService;
import com.example.yeczane.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerInfoService customerInfoService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerInfoService customerInfoService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.customerInfoService = customerInfoService;
        this.productService = productService;
    }

    @Override
    public Order addNewOrder(OrderDetailsDto orderDetailsDto, Long userId) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setAmount(1.0);
        orderDetails.setPrice(orderDetailsDto.getProduct().getPrice());
        orderDetails.setProduct(productService.getProductByCode(orderDetailsDto.getProduct().getCode()));
        Order orderToBeUpdated = getTemporaryOrderByUserId(userId);
        if (orderToBeUpdated==null){
            Order newOrder = new Order();
            newOrder.setOrderDate(new Date());
            newOrder.setCustomerInfo(customerInfoService.findCustomerInfoByUserId(userId));
            newOrder.setOrderDetails(List.of(orderDetails));
            orderDetails.setOrder(newOrder);
            return orderRepository.save(newOrder);
        } else {
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
    public Order getTemporaryOrderByUserId(Long userId) {
        List<Order> ordersByUserId = orderRepository.getAllTemporaryOrdersByUserId(userId);
        return ordersByUserId.isEmpty() ? null : orderRepository.getAllTemporaryOrdersByUserId(userId).get(0);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderRepository.getAllOrdersByUserId(userId);
    }
}
