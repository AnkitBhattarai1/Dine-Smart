package com.example.menu_service.menu_service.Services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.menu_service.menu_service.Model.MenuItem;
import com.example.menu_service.menu_service.dto.MenuItemRequest;
import com.example.menu_service.menu_service.dto.MenuItemResponse;

@Service
public interface MenuItemService {

    public MenuItem getItem(String id);

    public MenuItemResponse saveItem(MenuItemRequest request);

    public Set<MenuItemResponse> saveItems(Set<MenuItemRequest> requests);

    public Double getPrice(String id);

}
