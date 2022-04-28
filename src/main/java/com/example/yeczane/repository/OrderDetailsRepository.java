package com.example.yeczane.repository;

import com.example.yeczane.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails getOrderDetailsByProductCode(String productCode);
}
