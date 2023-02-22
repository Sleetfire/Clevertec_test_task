package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.repository.memory_repository.MemoryDiscountCardRepository;
import com.barkovsky.check_runner.repository.memory_repository.api.IDiscountCardRepository;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemoryDiscountCardServiceTest {

    @Mock
    private IDiscountCardRepository discountCardRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(discountCardRepository);
    }

    @ParameterizedTest(name = "{index} - arg: {0}")
    @MethodSource("getDiscountCard")
    @DisplayName("Adding discount card to the memory storage")
    @Disabled
    void checkAddShouldReturnDiscountCard(DiscountCard discountCard) {
        try (MockedStatic<MemoryDiscountCardRepository> discountCardRepositoryMock = Mockito.mockStatic(MemoryDiscountCardRepository.class)) {
            discountCardRepositoryMock.when(MemoryDiscountCardRepository::getInstance).thenReturn(discountCardRepository);

            IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();
            Mockito.when(discountCardRepository.add(Mockito.any(DiscountCard.class))).thenReturn(discountCard);

            DiscountCard discountCardFromDb = discountCardService.add(discountCard);
            Assertions.assertThat(discountCardFromDb).isEqualTo(discountCard);
        }
    }

    @ParameterizedTest(name = "{index} - arg: {0}")
    @MethodSource("getDiscountCard")
    @DisplayName("Getting discount card by id from the memory storage")
    void checkGetByIdShouldReturnDiscountCard(DiscountCard discountCard) {
        try (MockedStatic<MemoryDiscountCardRepository> discountCardRepositoryMock = Mockito.mockStatic(MemoryDiscountCardRepository.class)) {
            discountCardRepositoryMock.when(MemoryDiscountCardRepository::getInstance).thenReturn(discountCardRepository);

            IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();
            Mockito.when(discountCardRepository.get(1L)).thenReturn(discountCard);

            DiscountCard discountCardFromDb = discountCardService.get(1L);
            Assertions.assertThat(discountCardFromDb).isEqualTo(discountCard);
        }
    }

    @Test
    @DisplayName("Getting discount card by id from the memory storage")
    void checkGetByIdShouldThrowEssenceNotFoundException() {
        try (MockedStatic<MemoryDiscountCardRepository> discountCardRepositoryMock = Mockito.mockStatic(MemoryDiscountCardRepository.class)) {
            discountCardRepositoryMock.when(MemoryDiscountCardRepository::getInstance).thenReturn(discountCardRepository);

            IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();
            Mockito.when(discountCardRepository.get(5L)).thenReturn(null);

            Assertions.assertThatThrownBy(() -> {
                        discountCardService.get(5L);
                    }).isInstanceOf(EssenceNotFoundException.class)
                    .hasMessageContaining("Discount card with id: 5 doesn't exist");
        }
    }


    @ParameterizedTest(name = "{index} - arg: {0}")
    @MethodSource("getDiscountCards")
    @DisplayName("Getting discount card's list from memory storage")
    @Disabled
    void checkGetAllShouldReturnNotEmptyList(List<DiscountCard> discountCards) {
        try (MockedStatic<MemoryDiscountCardRepository> discountCardRepositoryMock = Mockito.mockStatic(MemoryDiscountCardRepository.class)) {
            discountCardRepositoryMock.when(MemoryDiscountCardRepository::getInstance).thenReturn(discountCardRepository);

            IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();
            Mockito.when(discountCardRepository.get()).thenReturn(discountCards);

            List<DiscountCard> checked = discountCardService.get();
            Assertions.assertThat(checked).isNotEmpty();
        }
    }

    @Test
    @DisplayName("Getting discount card's list from empty memory storage")
    void checkGetAllShouldThrowEssenceNotFoundException() {
        try (MockedStatic<MemoryDiscountCardRepository> discountCardRepositoryMock = Mockito.mockStatic(MemoryDiscountCardRepository.class)) {
            discountCardRepositoryMock.when(MemoryDiscountCardRepository::getInstance).thenReturn(discountCardRepository);

            IDiscountCardService discountCardService = MemoryDiscountCardService.getInstance();
            Mockito.when(discountCardRepository.get()).thenReturn(Collections.emptyList());

            List<DiscountCard> checked = discountCardService.get();
            Assertions.assertThat(checked).isEmpty();
        }
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