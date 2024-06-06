package com.example.menu_service.menu_service.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.menu_service.menu_service.Model.Menu;
import com.example.menu_service.menu_service.Model.MenuItem;
import com.example.menu_service.menu_service.Repo.MenuRepo;
import com.example.menu_service.menu_service.Services.MenuItemService;
import com.example.menu_service.menu_service.Services.MenuService;
import com.example.menu_service.menu_service.dto.MenuItemResponse;
import com.example.menu_service.menu_service.dto.MenuRequest;
import com.example.menu_service.menu_service.dto.MenuResponse;

import jakarta.transaction.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepo menuRepo;

    @Autowired
    MenuItemService menuItemService;

    private MenuResponse menuToMenuRes(Menu menu) {
        Set<MenuItemResponse> itemList = new HashSet<>();
        for (MenuItem item : menu.getMenuItems()) {
            itemList.add(
                    new MenuItemResponse(item.getId(), item.getName(), item.getDescription(), item.isGeneral(),
                            item.getPhotoLocation()));
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
        return menuToMenuRes(menuRepo.save(menReqToMenu(req)));
    }

    @Override
    public Menu getMenu(String id) {
        return menuRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

}
