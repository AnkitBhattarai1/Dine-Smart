package com.example.resturant_service.resturant_service.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.resturant_service.resturant_service.Models.Resturant;
import com.example.resturant_service.resturant_service.dto.ResturantRequest;
import com.example.resturant_service.resturant_service.dto.ResturantResponse;

@Service
public interface ResturantService {

    public ResturantResponse getResturantById(String id);

    public List<ResturantResponse> getAllResturants();

    public ResturantResponse save(ResturantRequest request);

    public Resturant getResturantWithId(String resturantId);
}
