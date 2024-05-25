package com.example.menu_service.menu_service.Services;

import org.springframework.stereotype.Service;

import com.example.menu_service.menu_service.Model.Menu;
import com.example.menu_service.menu_service.dto.MenuRequest;
import com.example.menu_service.menu_service.dto.MenuResponse;

@Service
public interface MenuService {

    public MenuResponse saveMenu(MenuRequest menu);

    public Menu getMenu(String id);
}
