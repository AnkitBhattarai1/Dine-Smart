package com.example.menu_service.menu_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.menu_service.menu_service.Model.Menu;
import com.example.menu_service.menu_service.Services.MenuService;
import com.example.menu_service.menu_service.dto.MenuRequest;
import com.example.menu_service.menu_service.dto.MenuResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService MenuService;

    public MenuController(MenuService menuService) {
        this.MenuService = menuService;
    }

    @GetMapping("/{id}")
    public Menu getMenu(@PathVariable(name = "id") String id) {
        return MenuService.getMenu(id);
    }

    @PostMapping()
    public MenuResponse saveMenu(@RequestBody MenuRequest request) {
        return MenuService.saveMenu(request);
    }

}
