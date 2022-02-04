package com.example.yeczane.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class ProductDto {
    private String name;
    private String code;
    private Double price;
    private Date createDate;
    private MultipartFile[] images;

    public ProductDto() {
    }
}
