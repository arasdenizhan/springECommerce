package com.example.yeczane.service;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService {
    Product addNewProduct(ProductDto productDto);
}
