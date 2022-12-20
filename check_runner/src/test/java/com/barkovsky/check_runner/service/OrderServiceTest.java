package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    @DisplayName("Test adding to order")
    void add() {
        IOrderService orderService = new OrderService();
        Product product = this.getTestProduct(false);
        orderService.add(product, 1);
        assertEquals(product, orderService.getProducts().get(0).getProduct());
    }

    @Test
    @DisplayName("Getting sum for pay from order")
    void getSumForPay() {
        IOrderService orderService1 = new OrderService();
        Product product1 = this.getTestProduct(true);
        IOrderService orderService2 = new OrderService();
        Product product2 = this.getTestProduct(false);
        DiscountCard discountCard = this.getTestDiscountCard();
        orderService2.addDiscountCard(discountCard);

        IOrderService orderService3 = new OrderService();
        Product product3 = this.getTestProduct(true);
        orderService3.addDiscountCard(discountCard);

        orderService1.add(product1, 10);
        orderService2.add(product2, 10);
        orderService3.add(product3, 10);

        assertEquals(13.5, orderService1.getSumForPay());
        assertEquals(13.5, orderService2.getSumForPay());
        assertEquals(12, orderService3.getSumForPay());
    }

    @Test
    @DisplayName("Getting amount of products from order")
    void getSum() {
        IOrderService orderService = new OrderService();
        Product product = this.getTestProduct(false);
        orderService.add(product, 2);
        assertEquals(3, orderService.getSum());
    }

    @Test
    @DisplayName("Getting discount from order")
    void getDiscount() {
        IOrderService orderService1 = new OrderService();
        Product product1 = this.getTestProduct(true);
        IOrderService orderService2 = new OrderService();
        Product product2 = this.getTestProduct(false);
        DiscountCard discountCard = this.getTestDiscountCard();
        orderService2.addDiscountCard(discountCard);

        IOrderService orderService3 = new OrderService();
        Product product3 = this.getTestProduct(true);
        orderService3.addDiscountCard(discountCard);

        orderService1.add(product1, 10);
        orderService2.add(product2, 10);
        orderService3.add(product3, 10);

        assertEquals(1.5, orderService1.getDiscount());
        assertEquals(1.5, orderService2.getDiscount());
        assertEquals(3, orderService3.getDiscount());
    }

    private Product getTestProduct(boolean isDiscount) {
        return Product.Builder.createBuilder()
                .setId(1)
                .setDescription("Apple")
                .setPrice(BigDecimal.valueOf(1.5))
                .setDiscount(isDiscount)
                .build();
    }

    private DiscountCard getTestDiscountCard() {
        return new DiscountCard(1234, 10);
    }
}