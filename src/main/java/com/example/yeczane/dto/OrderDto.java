package com.example.yeczane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    public Long id;
    public String date;
    public String orderStatus;
    public List<OrderDetailsDto> orderDetailsList;
    public Double totalPrice;
}
