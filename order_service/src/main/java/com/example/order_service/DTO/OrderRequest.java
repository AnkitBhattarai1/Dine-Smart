package com.example.order_service.DTO;

import java.util.Set;

public record OrderRequest(
        String customerId,
        String resturantId,
        Set<OrderItemRequest> orderItems) {

    public OrderRequest {

    }

}
