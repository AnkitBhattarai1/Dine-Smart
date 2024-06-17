package com.example.order_service.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderResponse(
        String orderId,
        String customerId,
        LocalDateTime orderDate,
        Set<OrderItemResponse> items,
        double totalPrice) {

}
