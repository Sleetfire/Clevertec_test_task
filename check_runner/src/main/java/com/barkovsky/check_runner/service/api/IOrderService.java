package com.barkovsky.check_runner.service.api;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.OrderProduct;
import com.barkovsky.check_runner.model.dto.Product;

import java.util.List;

public interface IOrderService {

    /**
     * Add product with count to order
     * @param product product for order
     * @param count count of products
     */
    void add(Product product, int count);

    /**
     * Getting list of products
     * @return list of products
     */
    List<OrderProduct> getProducts();

    /**
     * Add discount card to order
     * @param discountCard discount card for order
     */
    void addDiscountCard(DiscountCard discountCard);

    /**
     * Getting receipt
     * @return receipt
     */
    String getReceipt();

    /**
     * Getting sum for pay
     * @return value of sum for pay
     */
    double getSumForPay();

    /**
     * Getting all order sum without discount
     * @return all order sum without discount
     */
    double getSum();

    /**
     * Getting discount
     * @return discount
     */
    double getDiscount();

}
