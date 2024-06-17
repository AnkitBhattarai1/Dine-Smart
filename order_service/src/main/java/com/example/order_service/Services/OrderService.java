package com.example.order_service.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.Models.Order;

@Service
public interface OrderService {

    public Order placeOrder(@RequestBody OrderRequest orderRequest);
}
