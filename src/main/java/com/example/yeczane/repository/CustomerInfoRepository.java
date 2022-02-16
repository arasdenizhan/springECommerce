package com.example.yeczane.repository;

import com.example.yeczane.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
    CustomerInfo findCustomerInfoByUserId(Long id);
}
