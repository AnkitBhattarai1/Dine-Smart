package com.example.order_service.ServiceIMpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.DTO.OrderItemRequest;
import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.Models.Order;
import com.example.order_service.Models.OrderItem;
import com.example.order_service.Repo.OrderRepo;
import com.example.order_service.Services.OrderItemService;
import com.example.order_service.Services.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderRepo orderRepo;

    @Override
    @Transactional
    public Order placeOrder(OrderRequest request) {
        int price = 0;
        Order o = new Order();
        Order savedInstance = orderRepo.save(o);

        String userId = request.customerId();
        String resturantId = request.resturantId();
        // System.out.println(request.orderItems());
        Set<OrderItemRequest> orderItemRequests = request.orderItems();
        Set<OrderItem> orderItems = new HashSet<>();

        for (OrderItemRequest requestItem : orderItemRequests) {
            OrderItem saveOrderItem = orderItemService.saveOrderItem(requestItem, savedInstance);
            orderItems.add(saveOrderItem);
            price += saveOrderItem.getPrice() * saveOrderItem.getQuantity();
        }

        savedInstance.setCustomerId(userId);
        savedInstance.setTotalAmount(price);
        savedInstance.setResturantId(resturantId);
        savedInstance.setItems(orderItems);
        savedInstance.setOrderDate(LocalDateTime.now());

        return orderRepo.save(savedInstance);

    }

}
