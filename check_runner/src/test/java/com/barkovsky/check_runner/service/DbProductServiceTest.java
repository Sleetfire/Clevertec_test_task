package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
class DbProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    @DisplayName("Getting product")
    void get() {
        Product product = this.getTestProduct();
        Product saved = this.productService.add(product);
        Product fromDb = this.productService.get(saved.getId());
        assertEquals(saved.getId(), fromDb.getId());
        assertEquals(product.getDescription(), fromDb.getDescription());
        assertEquals(product.getPrice(), fromDb.getPrice());
        assertEquals(product.isDiscount(), fromDb.isDiscount());
    }

    private Product getTestProduct() {
        return Product.Builder.createBuilder()
                .setDescription("Apple")
                .setPrice(BigDecimal.valueOf(1.5))
                .setDiscount(false)
                .build();
    }

}