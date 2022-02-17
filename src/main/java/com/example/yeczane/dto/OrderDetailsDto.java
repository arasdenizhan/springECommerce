package com.example.yeczane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailsDto {
    public ProductDto product;
    public String amount;
    public String price;
}
