package com.example.yeczane.service;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ProductService {
    Product addNewProduct(MultipartFile[] imageArray, ProductDto productDto);
}
