package com.example.yeczane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailsDto {
    public Long id;
    public ProductDto product;
    public String amount;
    public String price;
    public Boolean delete;

    public OrderDetailsDto(Long id, ProductDto product, String amount, String price) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }
}
