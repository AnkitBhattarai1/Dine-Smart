package com.example.order_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.order_service.Models.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, String> {

}
