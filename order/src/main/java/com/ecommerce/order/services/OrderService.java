package com.ecommerce.order.services;


import com.ecommerce.order.dtos.OrderItemDto;
import com.ecommerce.order.dtos.OrderResponse;
import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.models.Order;
import com.ecommerce.order.models.OrderItem;
import com.ecommerce.order.models.OrderStatus;
import com.ecommerce.order.repositories.OrderRepository;
//import com.springacademy.ecartmicroservicesapp.model.*;
//import com.springacademy.ecartmicroservicesapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {


    private final CartService cartService;
  //  private  final UserRepository userRepository;
    private  final OrderRepository orderRepository;

//    @Autowired
//    public OrderService(CartService cartService, UserRepository userRepository, OrderRepository orderRepository) {
//        this.cartService = cartService;
//        this.userRepository = userRepository;
//        this.orderRepository = orderRepository;
//    }

    @Autowired
    public OrderService(CartService cartService, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }


    public Optional<OrderResponse>  createOrder(Long userId)
    {
      //Validate cart items

        List<CartItem> cartItems=cartService.getCart(userId);
        if(cartItems.isEmpty())
        {
            return Optional.empty();
        }
        //validate user
//        Optional<User> userOptional= userRepository.findById(Long.parseLong(userId));
//        if (userOptional.isEmpty())
//        {
//            return Optional.empty();
//        }
//        User user=userOptional.get();
        //Calculate total price

        BigDecimal totalPrice= cartItems.stream()
                .map(CartItem::getPrice)
               // .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        //create order

        Order order=new Order();
       // order.setUser(user);
        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);

        //set the order items
       List<OrderItem> orderItems=cartItems.stream()
               .map(item -> new OrderItem
                               (null,
                                      // item.getProduct(),
                                        item.getProductId(),
                                       item.getQuantity(),
                                       item.getPrice(),
                                       order)).
              toList();
      //  order.setItems(orderItems);
        Order savedOrder=orderRepository.save(order);

        //clear cart
        cartService.clearCart(userId);

        //Return of order response

        //Transfer "saveOrder" object to type of order response

       //return Optional.of(mapToOrderResponse(savedOrder));
        return Optional.of(new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                orderItems.stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProductId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        )).toList(),
                savedOrder.getCreatedAt()
        ));
    }}



   // private OrderResponse mapToOrderResponse(Order order) {
      //  return new OrderResponse(
             ///   order.getId(),
             //   order.getTotalAmount(),
             //   order.getStatus(),
              //  order.getItems().stream()
                  //      .map(orderItem -> new OrderItemDto(
                      //          orderItem.getId(),
                              //  orderItem.getProduct().getId(),
                  //              orderItem.getProductId(),
                 //               orderItem.getQuantity(),
                 //               orderItem.getPrice(),
               //                 orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
              //          )).toList(),
           //     order.getCreatedAt() // Removed the trailing comma here
    //    );

   // }
    //}
