package com.ecommerce.order.repositories;

import com.ecommerce.order.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Custom query methods can be defined here if needed
    // For example, to find cart items by user ID or product ID



   // CartItem findByUserAndProduct(User user, Product product);
   CartItem findByUserIdAndProductId(Long userId, Long productId);

   // void deleteByUserAndProduct(User user, Product product);
   //void deleteByUserIdAndProductId(String userId, String productId);

    void deleteByUserIdAndProductId(Long userId, Long productId);


    //  List<CartItem> findByUser(User user);

    List<CartItem> findByUserId(Long userId);


    //void deleteByUser(User user);

    void deleteByUserId(Long userId);

}