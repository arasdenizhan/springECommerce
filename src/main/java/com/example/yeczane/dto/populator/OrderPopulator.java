package com.example.yeczane.dto.populator;

import com.example.yeczane.dto.OrderDetailsDto;
import com.example.yeczane.dto.OrderDto;
import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Order;
import com.example.yeczane.model.OrderDetails;
import com.example.yeczane.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderPopulator {
    private OrderPopulator() {
        throw new UnsupportedOperationException();
    }

    public static OrderDetailsDto populateDetailsDto(OrderDetails orderDetails){
        ProductDto productDto = ProductPopulator.populateDto(orderDetails.getProduct());
        return new OrderDetailsDto(orderDetails.getId(), productDto, orderDetails.getAmount().toString(), orderDetails.getPrice().toString());
    }

    public static OrderDto populateOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        if(order!=null){
            orderDto.setOrderStatus(order.getOrderStatus().toString());
            orderDto.setDate(DateUtil.convertDateToString(order.getOrderDate()));
            List<OrderDetailsDto> orderDetailsDtoList = new ArrayList<>();
            order.getOrderDetails().forEach(orderDetails -> orderDetailsDtoList.add(populateDetailsDto(orderDetails)));
            orderDto.setOrderDetailsList(orderDetailsDtoList);
            orderDto.setTotalPrice(order.getOrderDetails().stream().map(OrderDetails::getPrice).reduce(Double::sum).orElse(0.0));
            orderDto.setId(order.getId());
        }
        return orderDto;
    }
}
