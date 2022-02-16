package com.example.yeczane.service.impl;

import com.example.yeczane.model.CustomerInfo;
import com.example.yeczane.repository.CustomerInfoRepository;
import com.example.yeczane.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    private final CustomerInfoRepository customerInfoRepository;

    @Autowired
    public CustomerInfoServiceImpl(CustomerInfoRepository customerInfoRepository) {
        this.customerInfoRepository = customerInfoRepository;
    }

    @Override
    public CustomerInfo saveCustomerInfo(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public CustomerInfo findCustomerInfoByUserId(Long id) {
        return customerInfoRepository.findCustomerInfoByUserId(id);
    }

    @Override
    public boolean isExistByUserId(Long userId) {
        return customerInfoRepository.findCustomerInfoByUserId(userId) != null;
    }
}
