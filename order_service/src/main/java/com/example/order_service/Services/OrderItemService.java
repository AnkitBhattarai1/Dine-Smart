package com.example.order_service.Services;

import org.springframework.stereotype.Service;

import com.example.order_service.DTO.OrderItemRequest;
import com.example.order_service.Models.Order;
import com.example.order_service.Models.OrderItem;

@Service
public interface OrderItemService {

    public OrderItem saveOrderItem(OrderItemRequest orderItemRequest, Order order);
}
