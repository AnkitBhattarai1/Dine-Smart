package com.example.resturant_service.resturant_service.ServicesImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.resturant_service.resturant_service.Exceptions.ResourceNotFoundException;
import com.example.resturant_service.resturant_service.Models.Resturant;
import com.example.resturant_service.resturant_service.Repo.ResturantRepo;
import com.example.resturant_service.resturant_service.Services.ResturantService;
import com.example.resturant_service.resturant_service.dto.ResturantRequest;
import com.example.resturant_service.resturant_service.dto.ResturantResponse;

import jakarta.transaction.Transactional;

@Service
public class ResturantServiceImpl implements ResturantService {

    private final ResturantRepo resturantRepo;
    private final RestTemplate restTemplate;

    public ResturantServiceImpl(ResturantRepo resturantRepo, RestTemplate restTemplate) {
        this.resturantRepo = resturantRepo;
        this.restTemplate = restTemplate;
    }

    private String createNewMenu(String resturantId) {
        String url = "http://MENU-SERVICE/menu/blank";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("resturantId", resturantId);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
        // System.out.println(response.getBody());
        return response.getBody();
    }

    private Resturant reqToRest(ResturantRequest request) {

        Resturant r = new Resturant();
        r.setName(request.name());
        r.setAddress(request.address());
        r.setEmail(request.email());
        r.setPhone(request.phone());
        r.setCratedDateTime(LocalDateTime.now());
        r.setLocation(request.location());
        // r.setMenuId(createNewMenu());
        r.setTables(null);

        return r;
    }

    private ResturantResponse restToRes(Resturant resturant) {
        return new ResturantResponse(resturant.getId(), resturant.getName(), resturant.getAddress(),
                resturant.getLocation(), resturant.getEmail(), resturant.getMenuId(), null);
    }

    @Override
    @Transactional
    public ResturantResponse save(ResturantRequest request) {

        if (resturantRepo.findByEmail(request.email()).isPresent())
            throw new IllegalArgumentException("A resturant with this email already exists");

        Resturant r = resturantRepo.save(reqToRest(request));
        r.setMenuId(createNewMenu(r.getId()));

        return restToRes(resturantRepo.save(r));
    }

    @Override
    public ResturantResponse getResturantById(String id) {

        Optional<Resturant> resturant = resturantRepo.findById(id);

        if (resturant.isPresent()) {
            return restToRes(resturant.get());
        }

        throw new ResourceNotFoundException("Resturant not found");
    }

    public Resturant getResturantWithId(String id) {
        Optional<Resturant> resturant = resturantRepo.findById(id);

        if (resturant.isPresent()) {
            return resturant.get();
        }

        throw new ResourceNotFoundException("Resturant not found");
    }

    @Override
    public List<ResturantResponse> getAllResturants() {
        List<Resturant> r = resturantRepo.findAll();
        List<ResturantResponse> res = new ArrayList<>();

        r.forEach((resturant) -> res.add(restToRes(resturant)));
        return res;
    }

}
