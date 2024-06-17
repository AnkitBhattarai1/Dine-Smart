package com.example.order_service.ServiceIMpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.order_service.DTO.OrderItemRequest;
import com.example.order_service.Models.Order;
import com.example.order_service.Models.OrderItem;
import com.example.order_service.Repo.OrderItemRepo;
import com.example.order_service.Services.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderItemRepo orderItemRepo;

    private double getPrice(String menuItemId) {
        String url = "http://MENU-SERVICE/menuItem/getPrice";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(url).queryParam("menuItemId", menuItemId);

        ResponseEntity<Double> responseEntity = restTemplate.getForEntity(builder.toUriString(), Double.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            if (responseEntity.getBody() != null)
                return responseEntity.getBody();
        }
        throw new IllegalArgumentException("Can't get price for menu item id: " + menuItemId);
    }

    @Override
    public OrderItem saveOrderItem(OrderItemRequest orderItemRequest, Order order) {
        OrderItem item = new OrderItem();
        item.setMenuitemId(orderItemRequest.menuItemId());
        item.setQuantity(orderItemRequest.quantity());
        item.setName(orderItemRequest.name());
        item.setPrice(getPrice(orderItemRequest.menuItemId()));
        item.setOrder(order);
        return orderItemRepo.save(item);
    }

}
