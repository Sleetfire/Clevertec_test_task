package com.barkovsky.check_runner.controller.rest;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", ""}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(this.productService.get(), HttpStatus.OK);
    }

}
