package com.barkovsky.check_runner.repository.memory_repository.util;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import com.barkovsky.check_runner.service.api.IProductService;

import java.math.BigDecimal;
import java.util.List;

public class MemoryDbUtil {

    private MemoryDbUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void init(IProductService productService, IDiscountCardService discountCardService) {
        List<DiscountCard> discountCards = List.of(new DiscountCard(1234, 10),
                new DiscountCard(5678, 10));
        List<Product> products = List.of(
                Product.Builder.createBuilder()
                        .setId(1)
                        .setDescription("Apple")
                        .setPrice(BigDecimal.valueOf(1))
                        .setDiscount(true)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(2)
                        .setDescription("Pear")
                        .setPrice(BigDecimal.valueOf(0.8))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(3)
                        .setDescription("Orange")
                        .setPrice(BigDecimal.valueOf(1.5))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(4)
                        .setDescription("Lemon")
                        .setPrice(BigDecimal.valueOf(3))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(5)
                        .setDescription("Banana")
                        .setPrice(BigDecimal.valueOf(2.1))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(6)
                        .setDescription("Cherry")
                        .setPrice(BigDecimal.valueOf(12.3))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(7)
                        .setDescription("Strawberry")
                        .setPrice(BigDecimal.valueOf(8.7))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(8)
                        .setDescription("Peach")
                        .setPrice(BigDecimal.valueOf(3.9))
                        .setDiscount(false)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(9)
                        .setDescription("Tangerine")
                        .setPrice(BigDecimal.valueOf(4))
                        .setDiscount(true)
                        .build(),
                Product.Builder.createBuilder()
                        .setId(10)
                        .setDescription("Tangerine")
                        .setPrice(BigDecimal.valueOf(6.2))
                        .setDiscount(false)
                        .build()
        );
        products.forEach(productService::add);
        discountCards.forEach(discountCardService::add);
    }

}
