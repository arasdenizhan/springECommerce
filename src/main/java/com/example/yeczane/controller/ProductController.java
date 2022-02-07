package com.example.yeczane.controller;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Product;
import com.example.yeczane.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestParam("images") MultipartFile[] multipartFiles, @RequestParam("product") String productDtoRawText){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            ProductDto productDto = objectMapper.readValue(productDtoRawText, ProductDto.class);
            Product product = productService.addNewProduct(multipartFiles, productDto);
            if(product.getId()!=null){
                return ResponseEntity.ok(product);
            }
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}
