package com.example.yeczane.repository;

import com.example.yeczane.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT orders.*, customer_info.user_id " +
            "from orders " +
            "inner join customer_info " +
            "where user_id =?1",
            nativeQuery = true)
    List<Order> getAllOrdersByUserId(Long userId);
}
