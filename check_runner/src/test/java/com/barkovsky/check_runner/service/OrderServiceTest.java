package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.OrderProduct;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IOrderService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.assertj.core.api.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class OrderServiceTest {

    private IOrderService orderService;

    @BeforeEach
    void setUp() {
        this.orderService = new OrderService();
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProduct")
    @DisplayName("Adding product to the order")
    void checkAddShouldReturnTrue(OrderProduct orderProduct) {
        Product product = orderProduct.getProduct();
        int productCount = orderProduct.getCount();
        this.orderService.add(product, productCount);
        Assertions.assertThat(product)
                .isEqualTo(this.orderService.getProducts().get(0).getProduct());
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProductList")
    @DisplayName("Getting not empty product list")
    void checkGetProductsShouldReturnNotEmptyList(List<OrderProduct> orderProductList) {

        orderProductList.forEach(p -> this.orderService.add(p.getProduct(), p.getCount()));

        List<OrderProduct> resultList = this.orderService.getProducts();

        Assertions.assertThat(resultList)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Getting empty products list")
    void checkGetProductsShouldReturnEmptyList() {
        List<OrderProduct> orderProductList = this.orderService.getProducts();

        Assertions.assertThat(orderProductList)
                .isEmpty();
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProduct")
    @DisplayName("Getting sum for pay for single product from receipt")
    void checkGetSumForPayForSingleProductShouldReturn2(OrderProduct orderProduct) {
        Product product = orderProduct.getProduct();
        int productCount = orderProduct.getCount();

        this.orderService.add(product, productCount);
        double sumForPay = this.orderService.getSumForPay();

        Assertions.assertThat(sumForPay)
                .isEqualTo(2);
    }

    @Test
    @DisplayName("Getting sum for pay from empty receipt")
    void checkGetSumForPayShouldReturn0() {
        double sumForPay = this.orderService.getSumForPay();

        Assertions.assertThat(sumForPay)
                .isZero();
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProductList")
    @DisplayName("Getting sum for pay from receipt without discount card")
    void checkGetSumForPayWithoutDiscountCardShouldReturn197and2(List<OrderProduct> orderProductList) {
        orderProductList.forEach(p -> this.orderService.add(p.getProduct(), p.getCount()));

        double sumForPay = this.orderService.getSumForPay();

        Assertions.assertThat(sumForPay)
                .isEqualTo(197.2);
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProductList")
    @DisplayName("Getting sum for pay from receipt with discount card")
    void checkGetSumForPayWithDiscountCardShouldReturn175and5(List<OrderProduct> orderProductList) {
        DiscountCard discountCard = DiscountCard.Builder.createBuilder()
                .setId(1234)
                .setDiscountPercent(10)
                .build();
        orderProductList.forEach(p -> this.orderService.add(p.getProduct(), p.getCount()));
        this.orderService.addDiscountCard(discountCard);

        double sumForPay = this.orderService.getSumForPay();

        Assertions.assertThat(sumForPay)
                .isEqualTo(175.5);
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProductList")
    @DisplayName("Getting product's cost sum")
    void checkGetSumShouldReturn197and2(List<OrderProduct> orderProductList) {
        orderProductList.forEach(p -> this.orderService.add(p.getProduct(), p.getCount()));

        double sum = this.orderService.getSumForPay();

        Assertions.assertThat(sum)
                .isEqualTo(197.2);
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProductList")
    @DisplayName("Getting discount without discount card")
    void checkGetDiscountWithoutDiscountCardShouldReturn19and8(List<OrderProduct> orderProductList) {
        orderProductList.forEach(p -> this.orderService.add(p.getProduct(), p.getCount()));

        double discount = this.orderService.getDiscount();

        Assertions.assertThat(discount)
                .isEqualTo(19.8, Offset.offset(0.0001));
    }

    @ParameterizedTest(name = "{index} - {0}")
    @MethodSource("getOrderProductList")
    @DisplayName("Getting discount with discount card")
    void checkGetDiscountWithDiscountCardShouldReturn41and5(List<OrderProduct> orderProductList) {

        DiscountCard discountCard = DiscountCard.Builder.createBuilder()
                .setId(1234)
                .setDiscountPercent(10)
                .build();
        orderProductList.forEach(p -> this.orderService.add(p.getProduct(), p.getCount()));
        this.orderService.addDiscountCard(discountCard);

        double discount = this.orderService.getDiscount();

        Assertions.assertThat(discount)
                .isEqualTo(41.5);
    }

    static Stream<OrderProduct> getOrderProduct() {
        Product product = Product.Builder.createBuilder()
                .setId(1)
                .setDescription("test-product1")
                .setPrice(BigDecimal.valueOf(1.0))
                .setDiscount(false)
                .build();
        int productCount = 2;
        OrderProduct orderProduct = new OrderProduct(product, productCount);
        return Stream.of(orderProduct);
    }

    static Stream<List<OrderProduct>> getOrderProductList() {

        Product product1 = Product.Builder.createBuilder()
                .setId(1)
                .setDescription("test-product1")
                .setPrice(BigDecimal.valueOf(1.0))
                .setDiscount(false)
                .build();
        int productCount1 = 2;

        Product product2 = Product.Builder.createBuilder()
                .setId(2)
                .setDescription("test-product2")
                .setPrice(BigDecimal.valueOf(2.5))
                .setDiscount(true)
                .build();
        int productCount2 = 4;

        Product product3 = Product.Builder.createBuilder()
                .setId(3)
                .setDescription("test-product3")
                .setPrice(BigDecimal.valueOf(0.7))
                .setDiscount(false)
                .build();
        int productCount3 = 10;

        Product product4 = Product.Builder.createBuilder()
                .setId(4)
                .setDescription("test-product4")
                .setPrice(BigDecimal.valueOf(33))
                .setDiscount(true)
                .build();
        int productCount4 = 6;

        OrderProduct orderProduct1 = new OrderProduct(product1, productCount1);
        OrderProduct orderProduct2 = new OrderProduct(product2, productCount2);
        OrderProduct orderProduct3 = new OrderProduct(product3, productCount3);
        OrderProduct orderProduct4 = new OrderProduct(product4, productCount4);
        List<OrderProduct> orderProductList = List.of(orderProduct1, orderProduct2, orderProduct3, orderProduct4);
        return Stream.of(orderProductList);
    }


}