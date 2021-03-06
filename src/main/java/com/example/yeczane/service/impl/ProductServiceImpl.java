package com.example.yeczane.service.impl;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.ProductImageDto;
import com.example.yeczane.dto.populator.ProductPopulator;
import com.example.yeczane.model.Product;
import com.example.yeczane.repository.ProductRepository;
import com.example.yeczane.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        Product productByCode = productRepository.findProductByCode(productDto.getCode());
        if(productByCode!=null){
            productDto.setCode(productByCode.getCode()+"1");
        }
        Product product = ProductPopulator.populateProduct(productDto);
        product.getImages().forEach(image -> image.setProduct(product));
        return productRepository.save(product);
    }

    @Override
    public List<ProductImageDto> getAllProducts() {
        List<ProductImageDto> productImageDtoList = new ArrayList<>();
        productRepository.findAll().forEach(product -> productImageDtoList.add(ProductPopulator.populateImageDto(product)));
        return productImageDtoList;
    }

    @Override
    public Product getProductByCode(String code) {
        return productRepository.findProductByCode(code);
    }
}
