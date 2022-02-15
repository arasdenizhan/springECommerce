package com.example.yeczane.service;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.ProductImageDto;
import com.example.yeczane.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductService {
    Product addNewProduct(ProductDto productDto);
    List<ProductImageDto> getAllProducts();
}
