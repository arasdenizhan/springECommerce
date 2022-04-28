package com.example.yeczane.controller;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Product;
import com.example.yeczane.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public String createProduct(@ModelAttribute("productDto") ProductDto productDto){
        Objects.requireNonNull(productDto, "Product cannot be null.");
        Product product = productService.addNewProduct(productDto);
        if(product!=null){
            return "product-success";
        }
        return "error";
    }
}
