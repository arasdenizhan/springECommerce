package com.example.yeczane.dto.populator;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.dto.OrderDto;
import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Order;
import com.example.yeczane.model.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderPopulator {
    private OrderPopulator() {
        throw new UnsupportedOperationException();
    }

    public static OrderDetailsDto populateDetailsDto(OrderDetails orderDetails){
        ProductDto productDto = ProductPopulator.populateDto(orderDetails.getProduct());
        return new OrderDetailsDto(productDto, orderDetails.getAmount().toString(), orderDetails.getPrice().toString());
    }

    public static OrderDto populateOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderStatus(order.getOrderStatus().toString());
        orderDto.setDate(order.getOrderDate().toString());
        List<OrderDetailsDto> orderDetailsDtoList = new ArrayList<>();
        order.getOrderDetails().forEach(orderDetails -> orderDetailsDtoList.add(populateDetailsDto(orderDetails)));
        orderDto.setOrderDetailsList(orderDetailsDtoList);
        return orderDto;
    }
}
