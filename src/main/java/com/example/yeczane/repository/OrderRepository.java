package com.example.yeczane.repository;

import com.example.yeczane.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT orders.*, customer_info.user_id " +
            "FROM orders " +
            "LEFT JOIN customer_info " +
            "ON customer_info_id = customer_info.id " +
           "WHERE customer_info.user_id = ?1 " +
            "AND orders.order_status!='TEMPORARY'"
            ,nativeQuery = true)
    List<Order> getAllOrdersByUserId(Long userId);

    @Query(value = "SELECT orders.*, customer_info.user_id " +
            "FROM orders " +
            "LEFT JOIN customer_info " +
            "ON customer_info_id = customer_info.id " +
            "WHERE customer_info.user_id = ?1 " +
            "AND orders.order_status='TEMPORARY'"
            ,nativeQuery = true)
    List<Order> getAllTemporaryOrdersByUserId(Long userId);
}
