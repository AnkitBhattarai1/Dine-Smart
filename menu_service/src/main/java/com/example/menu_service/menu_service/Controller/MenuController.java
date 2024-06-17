package com.example.menu_service.menu_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.menu_service.menu_service.Model.Menu;
import com.example.menu_service.menu_service.Services.MenuItemService;
import com.example.menu_service.menu_service.Services.MenuService;
import com.example.menu_service.menu_service.dto.MenuItemRequest;
import com.example.menu_service.menu_service.dto.MenuRequest;
import com.example.menu_service.menu_service.dto.MenuResponse;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    private final MenuItemService menuItemService;

    public MenuController(MenuService menuService, MenuItemService menuItemService) {
        this.menuService = menuService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("getMenuOfResturant")
    public ResponseEntity<?> getMenuOfResturant(@RequestParam(name = "resturantId") String resturantId) {
        try {
            return ResponseEntity.ok(menuService.getMenuOfResturant(resturantId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Menu getMenu(@PathVariable(name = "id") String id) {
        return menuService.getMenu(id);
    }

    @GetMapping("/blank")
    public ResponseEntity<String> createBlankMenu(@RequestParam(name = "resturantId") String resturnatId) {
        MenuResponse entity = menuService.createBlankMenu(resturnatId);
        return ResponseEntity.ok().body(entity.id());
    }

    @PostMapping()
    public MenuResponse saveMenu(@RequestBody MenuRequest request) {
        return menuService.saveMenu(request);
    }

    @PutMapping("updateMenu/{id}")
    public String putMethodName(@PathVariable String menuId, @RequestBody MenuRequest menu) {
        // Todo to be done later....;
        return null;
    }

    @PutMapping("/addMenuItem")
    public ResponseEntity<?> addMenuItem(@RequestParam(name = "menuId") String menuId,
            @RequestBody MenuItemRequest request) {
        try {
            return ResponseEntity.ok(menuService.addMenuItem(menuId, request));
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }

    }

}
