package com.example.yeczane.service;

import com.example.yeczane.model.CustomerInfo;

public interface CustomerInfoService {

    CustomerInfo saveCustomerInfo(CustomerInfo customerInfo);
    CustomerInfo findCustomerInfoByUserId(Long id);
    boolean isExistByUserId(Long userId);
}
