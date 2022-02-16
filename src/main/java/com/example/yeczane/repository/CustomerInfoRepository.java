package com.example.yeczane.repository;

import com.example.yeczane.model.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
    @Query(value = "SELECT * FROM customer_info WHERE customer_info.user_id = ?1", nativeQuery = true)
    CustomerInfo findCustomerInfoByUserId(Long id);
}
