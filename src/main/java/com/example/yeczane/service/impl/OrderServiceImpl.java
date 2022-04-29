package com.example.yeczane.service.impl;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.model.Order;
import com.example.yeczane.model.OrderDetails;
import com.example.yeczane.model.OrderStatus;
import com.example.yeczane.repository.OrderRepository;
import com.example.yeczane.service.CustomerInfoService;
import com.example.yeczane.service.OrderService;
import com.example.yeczane.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        }
        else {
            orderDetails.setOrder(orderToBeUpdated);
            Optional<OrderDetails> optionalOrderDetails = orderToBeUpdated.getOrderDetails().stream().filter(detail -> orderDetails.getProduct().getCode().equals(detail.getProduct().getCode())).findAny();
            if(optionalOrderDetails.isPresent()){
                OrderDetails orderDetailsFound = optionalOrderDetails.get();
                double newAmount = orderDetailsFound.getAmount() + orderDetails.getAmount();
                orderDetailsFound.setPrice(orderDetailsFound.getPrice()/orderDetailsFound.getAmount()*newAmount);
                orderDetailsFound.setAmount(newAmount);
                orderToBeUpdated.getOrderDetails().addAll(Collections.singletonList(orderDetailsFound));
            }
            else {
                orderToBeUpdated.getOrderDetails().addAll(Collections.singletonList(orderDetails));
            }
            return orderRepository.save(orderToBeUpdated);
        }
    }

    @Override
    public Order getTemporaryOrderByUserId(Long userId) {
        List<Order> ordersByUserId = orderRepository.getAllTemporaryOrdersByUserId(userId);
        return ordersByUserId.isEmpty() ? null : orderRepository.getAllTemporaryOrdersByUserId(userId).get(0);
    }

    @Override
    public Order finishOrder(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setOrderStatus(OrderStatus.RECEIVED);
            order.setOrderDate(new Date());
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        return orderRepository.getAllOrdersByUserId(userId);
    }
}
