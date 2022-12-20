package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryDiscountCardServiceTest {

    private final IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();

    @Test
    @DisplayName("Getting discount card")
    void get() {
        DiscountCard discountCard = this.getTestDiscountCard();
        this.discountCardService.add(discountCard);
        assertEquals(discountCard, this.discountCardService.get(discountCard.getId()));
    }

    private DiscountCard getTestDiscountCard() {
        return new DiscountCard(1234, 10);
    }
}