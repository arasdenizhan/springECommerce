package com.example.yeczane.repository;

import com.example.yeczane.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails getOrderDetailsByProductCode(String productCode);
    @Modifying
    @Transactional
    @Query("Delete from OrderDetails o where o.id=?1")
    void deleteOrderDetailsById(Long id);
}
