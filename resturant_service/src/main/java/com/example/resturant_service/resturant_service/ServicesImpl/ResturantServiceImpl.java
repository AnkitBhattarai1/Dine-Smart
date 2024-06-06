package com.example.resturant_service.resturant_service.ServicesImpl;

import com.example.resturant_service.resturant_service.Models.Resturant;
import com.example.resturant_service.resturant_service.Services.ResturantService;
import com.example.resturant_service.resturant_service.dto.ResturantRequest;
import com.example.resturant_service.resturant_service.dto.ResturantResponse;

public class ResturantServiceImpl implements ResturantService {

    private Resturant reqToRest(ResturantRequest request) {
        Resturant r = new Resturant();
        r.setName(request.name());
        r.setAddress(request.address());
        r.setEmail(request.email());
        r.setPhone(request.phone());
        r.setCratedDateTime(request.createdAt());
        r.setMenuId(request.menuId());

        return null;
    }

    private ResturantResponse restToRes(Resturant resturant) {
        return new ResturantResponse(resturant.getId(), resturant.getName(), resturant.getAddress(),
                resturant.getLocation(), resturant.getEmail(), null);
    }

    @Override
    public ResturantResponse save(ResturantRequest request) {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
