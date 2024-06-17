package com.example.order_service.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * OrderItemResponse
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderItemResponse(
                String id,
                String name,
                String menuitemId,
                int quantity,
                int price,
                String orderId) {
}
