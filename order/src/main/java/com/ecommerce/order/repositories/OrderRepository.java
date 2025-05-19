package com.ecommerce.order.repositories;

import com.ecommerce.order.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find orders by user ID or order status
}