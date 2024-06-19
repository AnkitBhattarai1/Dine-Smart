package com.example.menu_service.menu_service.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.menu_service.menu_service.Services.MenuItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/menuItem")
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @GetMapping("/getPrice")
    public ResponseEntity<Double> getPriceOf(@RequestParam(name = "menuItemId") String menuItemId) {
        try {
            return ResponseEntity.ok().body(menuItemService.getPrice(menuItemId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
            // TODO: handle exception
        }
    }

}
