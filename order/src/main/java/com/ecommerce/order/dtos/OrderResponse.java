package com.ecommerce.order.dtos;


import com.ecommerce.order.models.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> items;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public OrderResponse(Long id, BigDecimal totalAmount, OrderStatus status, List<OrderItemDto> list, LocalDateTime createdAt) {

        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = list;
        this.createdAt = createdAt;

    }
}
