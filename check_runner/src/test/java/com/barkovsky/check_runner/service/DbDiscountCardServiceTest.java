package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
class DbDiscountCardServiceTest {

    @Autowired
    private IDiscountCardService discountCardService;

    @Test
    @DisplayName("Getting discount card")
    void get() {
        DiscountCard discountCard = this.getTestDiscountCard();
        DiscountCard saved = this.discountCardService.add(discountCard);
        DiscountCard fromDb = this.discountCardService.get(saved.getId());
        assertEquals(saved.getId(), fromDb.getId());
        assertEquals(discountCard.getDiscountPercent(), fromDb.getDiscountPercent());
    }

    @Test
    @DisplayName("Throwing not found exception")
    void throwNotFoundException() {
        EssenceNotFoundException exception = assertThrows(EssenceNotFoundException.class,
                () -> this.discountCardService.get(100));
        assertEquals("Discount card with id: 100 doesn't exist", exception.getMessage());
    }

    private DiscountCard getTestDiscountCard() {
        return DiscountCard.Builder.createBuilder()
                .setDiscountPercent(10)
                .build();
    }

}