package com.example.resturant_service.resturant_service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resturant_service.resturant_service.Services.ResturantService;
import com.example.resturant_service.resturant_service.dto.ResturantRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/resturant")
public class ResturantController {

    private final ResturantService resturantService;

    public ResturantController(ResturantService resturantService) {
        this.resturantService = resturantService;
    }

    /*
     * /resturant Post for saving a resturant....
     */
    @PostMapping("/save")
    public ResponseEntity<?> saveResturant(@RequestBody ResturantRequest resturantRequest) {
        return new ResponseEntity<>(resturantService.save(resturantRequest), HttpStatus.CREATED);
        // try {
        // ResturantResponse r = resturantService.save(resturantRequest);
        // return ResponseEntity.status(HttpStatus.CREATED)
        // .header("Content-Type", "application/json")
        // .body(r);
        // } catch (Exception e) {
        // e.printStackTrace();
        // throw new RuntimeException("Error saving resturant");
        // }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResturant(@PathVariable(name = "id") String id) {
        try {
            return ResponseEntity.ok().body(resturantService.getResturantById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            // TODO: handle exception
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getMethodName() {
        try {
            return ResponseEntity.ok().body(resturantService.getAllResturants());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            // TODO: handle exception
        }
    }

}
