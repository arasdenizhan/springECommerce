package com.example.yeczane.service.impl;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.dto.populator.ProductPopulator;
import com.example.yeczane.model.Product;
import com.example.yeczane.repository.ProductRepository;
import com.example.yeczane.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addNewProduct(MultipartFile[] imageArray, ProductDto productDto) {
        productDto.setImages(imageArray);
        Product product = ProductPopulator.populate(productDto);
        product.getImages().forEach(image -> image.setProduct(product));
        return productRepository.save(product);
    }
}
