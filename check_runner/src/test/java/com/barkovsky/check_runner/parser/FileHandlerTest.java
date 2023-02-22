package com.barkovsky.check_runner.parser;

import com.barkovsky.check_runner.exception.DeserializationException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    private FileHandler fileHandle;

    @BeforeEach
    void setUp() {
        this.fileHandle = new FileHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Read products from file")
    void checkReadProductShouldReturnNotEmptyNotEmptyList() {
        List<Product> products = this.fileHandle.readProduct("product.txt");

        Assertions.assertThat(products).isNotEmpty();
    }

    @Test
    @DisplayName("Read products from file should throw DeserializationException")
    void checkReadProductShouldThrowDeserializationException() {
        Assertions.assertThatThrownBy(() -> this.fileHandle.readProduct("product-error.txt"))
                .isInstanceOf(DeserializationException.class);
    }

    @Test
    @DisplayName("Read discount cards from file")
    void checkReadCardShouldReturnNotEmptyList() {
        List<DiscountCard> discountCards = this.fileHandle.readCard("card.txt");

        Assertions.assertThat(discountCards).isNotEmpty();
    }

    @Test
    @DisplayName("Read discount cards from file should throw DeserializationException")
    void checkReadDiscountCardShouldThrowDeserializationException() {
        Assertions.assertThatThrownBy(() -> this.fileHandle.readCard("card-error.txt"))
                .isInstanceOf(DeserializationException.class);
    }

    @Test
    @DisplayName("Write discount cards to the file")
    void checkWriteShouldCreateNewFile() {
        DiscountCard discountCard1 = DiscountCard.Builder.createBuilder()
                .setId(1L)
                .setDiscountPercent(10)
                .build();
        DiscountCard discountCard2 = DiscountCard.Builder.createBuilder()
                .setId(2L)
                .setDiscountPercent(20)
                .build();
        List<DiscountCard> discountCards = List.of(discountCard1, discountCard2);

        String fileName = "writing-test.txt";
        this.fileHandle.write(fileName, discountCards);

        List<DiscountCard> checked = this.fileHandle.readCard(fileName);

        Assertions.assertThat(checked).isEqualTo(discountCards);
    }
}