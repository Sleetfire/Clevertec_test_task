package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.ConversionException;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.repository.db_repository.api.IDbDiscountCardRepository;
import com.barkovsky.check_runner.repository.db_repository.entity.DiscountCardEntity;
import com.barkovsky.check_runner.repository.memory_repository.api.IDiscountCardRepository;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DbDiscountCardServiceTest {

    @MockBean
    private IDbDiscountCardRepository discountCardRepository;

    @Autowired
    private IDiscountCardService discountCardService;

    @Autowired
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getDiscountCard")
    @DisplayName("Adding discount card to the db storage")
    void checkAddDiscountCardToDataBaseStorageShouldReturnDiscountCard(DiscountCard discountCard) {
        DiscountCardEntity discountCardEntity = this.conversionService.convert(discountCard, DiscountCardEntity.class);
        Mockito.when(discountCardRepository.save(Mockito.any(DiscountCardEntity.class))).thenReturn(discountCardEntity);

        DiscountCard dbDiscountCard = this.discountCardService.add(discountCard);

        Assertions.assertThat(dbDiscountCard).isEqualTo(discountCard);
    }

    @Test
    @DisplayName("Adding null to the db discount card storage")
    void checkAddDiscountCardToDatabaseStorageShouldThrowConversionException() {
        Assertions.assertThatThrownBy(() -> discountCardService.add(null))
                .isInstanceOf(ConversionException.class)
                .hasMessageContaining("Essence conversion error");
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getDiscountCard")
    @DisplayName("Getting discount card by id from the db storage")
    void checkGetDiscountCardByIdShouldReturnDiscountCard(DiscountCard discountCard) {
        DiscountCardEntity discountCardEntity = this.conversionService.convert(discountCard, DiscountCardEntity.class);
        Mockito.when(this.discountCardRepository.findById(1010L)).thenReturn(Optional.ofNullable(discountCardEntity));

        DiscountCard dbDiscountCard = this.discountCardService.get(1010L);

        Assertions.assertThat(dbDiscountCard).isEqualTo(discountCard);
    }

    @Test
    @DisplayName("Getting discount card by id from the db storage")
    void checkGetDiscountCardByIdShouldThrowEssenceNotFoundException() {
        Mockito.when(this.discountCardRepository.findById(1010L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> discountCardService.get(1010L))
                .isInstanceOf(EssenceNotFoundException.class)
                .hasMessageContaining("Discount card with id: 1010 doesn't exist");
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getDiscountCards")
    @DisplayName("Getting discount card's list from the db storage")
    void checkGetALlDiscountCardShouldReturnListSizeMoreThenZero(List<DiscountCard> discountCardList) {
        List<DiscountCardEntity> discountCardEntityList = new ArrayList<>();
        discountCardList.forEach(discountCard -> discountCardEntityList.add(this.conversionService.convert(discountCard, DiscountCardEntity.class)));
        Mockito.when(this.discountCardRepository.findAll()).thenReturn(discountCardEntityList);

        int size = this.discountCardService.get().size();

        Assertions.assertThat(size).isPositive();
    }

    @Test
    @DisplayName("Getting discount card's list from the db storage with EssenceNotFoundException")
    void checkGetALlDiscountCardShouldThrowEssenceNotFoundException() {
        Mockito.when(this.discountCardRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThatThrownBy(() -> this.discountCardService.get())
                .isInstanceOf(EssenceNotFoundException.class)
                .hasMessageContaining("Discount cards don't exist");

    }

    static Stream<DiscountCard> getDiscountCard() {
        DiscountCard discountCard = DiscountCard.Builder.createBuilder()
                .setId(1010)
                .setDiscountPercent(10)
                .build();
        return Stream.of(discountCard);
    }

    static Stream<List<DiscountCard>> getDiscountCards() {
        DiscountCard discountCard1 = DiscountCard.Builder.createBuilder()
                .setId(1010)
                .setDiscountPercent(10)
                .build();

        DiscountCard discountCard2 = DiscountCard.Builder.createBuilder()
                .setId(1015)
                .setDiscountPercent(15)
                .build();

        DiscountCard discountCard3 = DiscountCard.Builder.createBuilder()
                .setId(1020)
                .setDiscountPercent(20)
                .build();
        List<DiscountCard> discountCardList = List.of(discountCard1, discountCard2, discountCard3);
        return Stream.of(discountCardList);
    }
}