package com.example.order_service.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderItemRequest(

        String name,
        String menuItemId,
        int quantity

) {

}
