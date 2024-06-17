package com.example.menu_service.menu_service.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.menu_service.menu_service.Model.MenuItem;
import com.example.menu_service.menu_service.Repo.MenuItemRepo;
import com.example.menu_service.menu_service.Services.MenuItemService;
import com.example.menu_service.menu_service.dto.MenuItemRequest;
import com.example.menu_service.menu_service.dto.MenuItemResponse;
import com.example.menu_service.menu_service.dto.MenuResponse;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    MenuItemRepo menuItemRepo;

    private MenuItem itemReqToitem(MenuItemRequest request) {
        MenuItem m = new MenuItem();
        m.setName(request.name());
        m.setDescription(request.description());
        m.setGeneral(false);
        m.setPhotoLocation(request.photoLocation());
        m.setVegetarian(request.isVegetarian());
        m.setPrice(request.price());
        m.setAvailable(request.available());
        m.setCategory(request.category());
        m.setIngredients(request.ingredients());
        return m;
    }

    private MenuItem addGenreal(MenuItemRequest request) {
        MenuItem m = itemReqToitem(request);
        m.setGeneral(true);
        return m;
    }

    private MenuItemResponse itemToitemres(MenuItem item) {
        return new MenuItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPhotoLocation(),
                item.isVegetarian(), item.getPrice(), item.getAvailable(), item.getCategory(), item.getIngredients());
    }

    @Override
    public MenuItem getItem(String id) {
        return menuItemRepo.findById(id).orElseThrow(() -> new AssertionError());// Exception is to be done
    }

    @Override
    public MenuItemResponse saveItem(MenuItemRequest request) {
        return itemToitemres(menuItemRepo.save(itemReqToitem(request)));
    }

    @Override
    public Set<MenuItemResponse> saveItems(Set<MenuItemRequest> requests) {
        Set<MenuItemResponse> response = new HashSet<>();
        for (MenuItemRequest m : requests) {
            response.add(saveItem(m));
        }
        return response;
    }

    @Override
    public Double getPrice(String id) {
        return menuItemRepo.findById(id)
                .orElseThrow(() -> new AssertionError())
                .getPrice();
    }

}
