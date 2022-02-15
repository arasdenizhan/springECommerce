package com.example.yeczane.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductImageDto {
    public String name;
    public String code;
    public Double price;
    public List<String> images;
}
