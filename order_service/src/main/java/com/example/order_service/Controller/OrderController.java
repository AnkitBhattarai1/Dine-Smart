package com.example.order_service.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.ResturantIdAndTableNO;
import com.example.order_service.Services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public ResturantIdAndTableNO getMethodName(@RequestParam(name = "resturantId") String param,
            @RequestParam(name = "tableId") int tableNo) {
        return new ResturantIdAndTableNO(param, tableNo);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            return ResponseEntity.ok().body(orderService.placeOrder(orderRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
