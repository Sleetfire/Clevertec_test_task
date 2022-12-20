package com.barkovsky.check_runner.service.api;

import com.barkovsky.check_runner.model.dto.Product;

import java.util.List;
import java.util.Map;

public interface IShopService {

    /**
     * Parsing params for receipt calculating
     * @param items params for order
     * @return receipt
     */
    String makeOrder(Map<String, String> items);

    /**
     * Getting list of products
     * @return list of products
     */
    List<Product> getProducts();

}
