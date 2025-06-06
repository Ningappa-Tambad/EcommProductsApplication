package com.ecommerce.order.controllers;

import com.ecommerce.order.dtos.OrderResponse;
import com.ecommerce.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class OrderController

{
    // Inject the OrderService here
     private final OrderService orderService;

     @Autowired
     public OrderController(OrderService orderService) {
         this.orderService = orderService;

     }

    // Define endpoints for creating, updating, and retrieving orders here
    // For example:

     @PostMapping(value = "/orders", produces = "application/json")

     public ResponseEntity<OrderResponse> createOrder(
             @RequestHeader ("X-User-Id") Long userId) {
         return orderService.createOrder(userId)
                .map(orderResponse -> new ResponseEntity<>(orderResponse, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
     }




}
