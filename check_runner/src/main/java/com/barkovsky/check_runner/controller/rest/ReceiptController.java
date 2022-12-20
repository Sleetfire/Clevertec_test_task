package com.barkovsky.check_runner.controller.rest;

import com.barkovsky.check_runner.service.api.IShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final IShopService shopService;

    public ReceiptController(IShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = {"/", ""},
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> create(@RequestBody Map<String, String> items) {
        return new ResponseEntity<>(this.shopService.makeOrder(items), HttpStatus.CREATED);
    }

}
