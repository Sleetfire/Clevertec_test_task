package com.barkovsky.check_runner.controller.rest;

import com.barkovsky.check_runner.service.api.IShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final IShopService shopService;

    public ReceiptController(IShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = {"/", ""},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> create(@RequestBody Map<String, String> items) {
        Map<String, String> data = new HashMap<>();
        data.put("data", this.shopService.makeOrder(items));
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

}
