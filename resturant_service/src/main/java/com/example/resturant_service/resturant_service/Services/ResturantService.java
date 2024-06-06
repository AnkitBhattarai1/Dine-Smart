package com.example.resturant_service.resturant_service.Services;

import org.springframework.stereotype.Service;

import com.example.resturant_service.resturant_service.dto.ResturantRequest;
import com.example.resturant_service.resturant_service.dto.ResturantResponse;

@Service
public interface ResturantService {

    public ResturantResponse save(ResturantRequest request);
}
