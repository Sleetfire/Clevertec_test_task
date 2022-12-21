package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MemoryProductServiceTest {

    private final IProductService productService = MemoryProductService.getInstance();

    @Test
    @DisplayName("Getting product")
    void get() {
        Product product = this.getTestProduct();
        this.productService.add(product);
        assertEquals(product, this.productService.get(product.getId()));
    }

    @Test
    @DisplayName("Throwing not found exception")
    void throwNotFoundException() {
        EssenceNotFoundException exception = assertThrows(EssenceNotFoundException.class,
                () -> this.productService.get(100));
        assertEquals("Product with id: 100 doesn't exist", exception.getMessage());
    }

    private Product getTestProduct() {
        return Product.Builder.createBuilder()
                .setId(1)
                .setDescription("Apple")
                .setPrice(BigDecimal.valueOf(1.5))
                .setDiscount(true)
                .build();
    }
}