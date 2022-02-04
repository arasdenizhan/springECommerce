package com.example.yeczane.dto.populator;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Product;

import java.util.Date;

public class ProductPopulator {
    private ProductPopulator() {
        throw new UnsupportedOperationException();
    }

    public static Product populate(ProductDto productDto){
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setPrice(productDto.getPrice());
        product.setCreateDate(new Date());
        product.setName(productDto.getName());
        product.setImages(ImagePopulator.populate(productDto));
        return product;
    }
}
