package com.example.menu_service.menu_service.ServiceImpl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.menu_service.menu_service.Model.Menu;
import com.example.menu_service.menu_service.Model.MenuItem;
import com.example.menu_service.menu_service.Repo.MenuRepo;
import com.example.menu_service.menu_service.Services.MenuItemService;
import com.example.menu_service.menu_service.Services.MenuService;
import com.example.menu_service.menu_service.dto.MenuItemRequest;
import com.example.menu_service.menu_service.dto.MenuItemResponse;
import com.example.menu_service.menu_service.dto.MenuRequest;
import com.example.menu_service.menu_service.dto.MenuResponse;
import com.example.menu_service.menu_service.dto.Resturant;

import jakarta.transaction.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepo menuRepo;

    @Autowired
    MenuItemService menuItemService;

    @Autowired
    RestTemplate restTemplate;

    private MenuResponse menuToMenuRes(Menu menu) {
        Set<MenuItemResponse> itemList = new HashSet<>();

        for (MenuItem item : menu.getMenuItems()) {
            itemList.add(
                    new MenuItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPhotoLocation(),
                            item.isVegetarian(), item.getPrice(), item.getAvailable(), item.getCategory(),
                            item.getIngredients()));
        }
        return new MenuResponse(menu.getId(), itemList);
    }

    private Menu menReqToMenu(MenuRequest req) {
        Set<MenuItemResponse> res = menuItemService.saveItems(req.menuItems());

        Set<MenuItem> menuitems = new HashSet<>();

        res.forEach((item) -> {
            menuitems.add(menuItemService.getItem(item.id()));
        });

        Menu m = new Menu();
        m.setMenuItems(menuitems);
        m.setResturantId(req.resturantId());

        return m;
    }

    @Override
    @Transactional
    public MenuResponse saveMenu(MenuRequest req) {
        String resturantId = req.resturantId();
        String url = "http://RESTURANT-SERVICE/resturant/" + resturantId;
        ResponseEntity<Resturant> response = restTemplate.getForEntity(url, Resturant.class);
        // Resturant resturant = restTemplate.getForObject(url, Resturant.class);
        if (response.getBody() == null)
            throw new IllegalArgumentException("Cannot find the resturant");

        Menu m = menuRepo.findByResturantId(resturantId)
                .orElseThrow(() -> new IllegalArgumentException("The resturant does not exist"));
        Menu request = menReqToMenu(req);
        m.setMenuItems(request.getMenuItems());
        return menuToMenuRes(menuRepo.save(m));
    }

    @Override
    public MenuResponse createBlankMenu(String resturantId) {
        Menu m = new Menu();
        m.setResturantId(resturantId);
        return menuToMenuRes(menuRepo.save(m));
    }

    @Override
    public Menu getMenu(String id) {
        return menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public MenuResponse getMenuOfResturant(String restaurantId) {

        Optional<Menu> m = menuRepo.findByResturantId(restaurantId);

        if (!m.isPresent())
            throw new IllegalArgumentException("Cannot find the menu");

        return menuToMenuRes(m.get());
    }

    @Override
    public MenuResponse addMenuItem(String id, MenuItemRequest menu) {

        Menu m = getMenu(id);
        MenuItemResponse item = menuItemService.saveItem(menu);
        MenuItem savedItem = menuItemService.getItem(item.id());
        m.getMenuItems().add(savedItem);

        Menu savedMenu = menuRepo.save(m);

        return menuToMenuRes(savedMenu);

    }

}
