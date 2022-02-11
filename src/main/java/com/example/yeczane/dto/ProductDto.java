package com.example.yeczane.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductDto {
    public String name;
    public String code;
    public Double price;
    public MultipartFile[] images;
}
