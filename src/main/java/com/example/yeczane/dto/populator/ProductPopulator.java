package com.example.yeczane.dto.populator;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.ProductImageDto;
import com.example.yeczane.model.Product;

import java.util.Date;

public class ProductPopulator {
    private ProductPopulator() {
        throw new UnsupportedOperationException();
    }

    public static Product populateProduct(ProductDto productDto){
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setPrice(productDto.getPrice());
        product.setCreateDate(new Date());
        product.setName(productDto.getName());
        product.setImages(ImagePopulator.populateImage(productDto));
        return product;
    }

    public static ProductDto populateDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setCode(product.getCode());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public static ProductImageDto populateImageDto(Product product){
        ProductImageDto productImageDto = new ProductImageDto();
        productImageDto.setName(product.getName());
        productImageDto.setCode(product.getCode());
        productImageDto.setPrice(product.getPrice());
        productImageDto.setImages(ImagePopulator.getRawData(product));
        return productImageDto;
    }
}
