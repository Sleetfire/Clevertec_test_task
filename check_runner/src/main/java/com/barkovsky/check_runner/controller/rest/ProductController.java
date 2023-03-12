package com.barkovsky.check_runner.controller.rest;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", ""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, List<Product>>> getAll() {
        Map<String, List<Product>> data = new HashMap<>();
        data.put("data", this.productService.get());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
