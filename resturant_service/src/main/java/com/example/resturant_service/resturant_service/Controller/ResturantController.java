package com.example.resturant_service.resturant_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resturant_service.resturant_service.dto.ResturantRequest;
import com.example.resturant_service.resturant_service.dto.ResturantResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/resturant")
public class ResturantController {
    /*
     * /resturant Post for saving a resturant....
     */
    @PostMapping("")
    public ResturantResponse postMethodName(@RequestBody ResturantRequest resturantRequest) {
        // TODO: process POST request

        return null;
    }

}
