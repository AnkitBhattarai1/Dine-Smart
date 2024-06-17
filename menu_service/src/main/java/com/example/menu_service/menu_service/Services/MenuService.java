package com.example.menu_service.menu_service.Services;

import org.springframework.stereotype.Service;

import com.example.menu_service.menu_service.Model.Menu;
import com.example.menu_service.menu_service.dto.MenuItemRequest;
import com.example.menu_service.menu_service.dto.MenuRequest;
import com.example.menu_service.menu_service.dto.MenuResponse;

@Service
public interface MenuService {

    public MenuResponse saveMenu(MenuRequest menu);

    public Menu getMenu(String id);

    public MenuResponse getMenuOfResturant(String restaurantId);

    public MenuResponse createBlankMenu(String resturantId);

    public MenuResponse addMenuItem(String id, MenuItemRequest menu);
}
