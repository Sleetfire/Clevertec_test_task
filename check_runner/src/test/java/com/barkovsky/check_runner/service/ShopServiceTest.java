package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import com.barkovsky.check_runner.service.api.IProductService;
import com.barkovsky.check_runner.service.api.IShopService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ShopServiceTest {

    @MockBean
    private IProductService productService;

    @MockBean
    private IDiscountCardService discountCardService;

    @Autowired
    private IShopService shopService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getTestMap")
    @DisplayName("Make order from map with discount card")
    void checkMakeOrderWithMapWithDiscountCardShouldReturnReceipt(Map<String, String> testMap) {
        Product product = Product.Builder.createBuilder()
                .setId(1L)
                .setDescription("test")
                .setPrice(BigDecimal.valueOf(2))
                .setDiscount(true)
                .build();
        DiscountCard discountCard = DiscountCard.Builder.createBuilder()
                .setId(2222L)
                .setDiscountPercent(10)
                .build();
        Mockito.when(this.productService.get(1L)).thenReturn(product);
        Mockito.when(this.discountCardService.get(2222L)).thenReturn(discountCard);

        String receipt = this.shopService.makeOrder(testMap);
        Assertions.assertThat(receipt)
                .contains("$20,00")
                .contains("-$4,00")
                .contains("$16,00");
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getTestMapWithOutDiscountCard")
    @DisplayName("Make order from map without discount card")
    @Disabled
    void checkMakeOrderWithMapWithoutDiscountCardShouldReturnReceipt(Map<String, String> testMap) {
        Product product = Product.Builder.createBuilder()
                .setId(1L)
                .setDescription("test")
                .setPrice(BigDecimal.valueOf(2))
                .setDiscount(false)
                .build();
        Mockito.when(this.productService.get(1L)).thenReturn(product);

        String receipt = this.shopService.makeOrder(testMap);
        Assertions.assertThat(receipt)
                .doesNotContain("DISCOUNT", "-$4,00")
                .contains("$20,00");
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getProductList")
    @DisplayName("Getting products list")
    void checkGetProductsShouldReturnNotEmptyList(List<Product> products) {
        Mockito.when(this.productService.get()).thenReturn(products);

        List<Product> productList = this.shopService.getProducts();
        Assertions.assertThat(productList).isNotEmpty();
    }

    static Stream<Map<String, String>> getTestMap() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "10");
        testMap.put("card", "2222");
        return Stream.of(testMap);
    }

    static Stream<Map<String, String>> getTestMapWithOutDiscountCard() {
        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "10");
        return Stream.of(testMap);
    }

    static Stream<List<Product>> getProductList() {

        Product product1 = Product.Builder.createBuilder()
                .setId(1)
                .setDescription("test-product1")
                .setPrice(BigDecimal.valueOf(1.0))
                .setDiscount(false)
                .build();

        Product product2 = Product.Builder.createBuilder()
                .setId(2)
                .setDescription("test-product2")
                .setPrice(BigDecimal.valueOf(2.5))
                .setDiscount(true)
                .build();

        List<Product> productList = List.of(product1, product2);
        return Stream.of(productList);
    }
}