package com.example.order_service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.order_service.Models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {

}
