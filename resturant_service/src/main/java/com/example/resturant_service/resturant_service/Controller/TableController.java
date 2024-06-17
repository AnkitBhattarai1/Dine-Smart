package com.example.resturant_service.resturant_service.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.resturant_service.resturant_service.Services.TableService;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTables(@RequestParam(name = "resturantId") String resturantId,
            @RequestParam(name = "capacity", defaultValue = "1") int number) {

        try {

            return ResponseEntity.ok(tableService.createNewTables(resturantId, number));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("" + e.getMessage());
        }
    }

    @GetMapping(path = "/createqr", produces = { IMAGE_PNG_VALUE })
    public ResponseEntity<byte[]> createQr(@RequestParam(name = "tableId") String tableId) {
        try {
            return ResponseEntity.ok().body(tableService.crateqrcode(tableId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new byte[0]);
            // TODO: handle exception
        }
    }

}
