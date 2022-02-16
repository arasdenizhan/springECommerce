package com.example.yeczane.service;

import com.example.yeczane.model.CustomerInfo;

public interface CustomerInfoService {
    CustomerInfo findCustomerInfoByUserId(Long id);
}
