package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.ValidationException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import com.barkovsky.check_runner.service.api.IOrderService;
import com.barkovsky.check_runner.service.api.IProductService;
import com.barkovsky.check_runner.service.api.IShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopService implements IShopService {

    private final IProductService productService;
    private final IDiscountCardService discountCardService;
    private final IOrderService orderService = new FileOrderDecoratorService(new OrderService());
    private Logger logger = LoggerFactory.getLogger(ShopService.class);

    public ShopService(IProductService productService, IDiscountCardService discountCardService) {
        this.productService = productService;
        this.discountCardService = discountCardService;
    }

    @Override
    public String makeOrder(Map<String, String> items) {
        DiscountCard discountCard = null;
        for (Map.Entry<String, String> item : items.entrySet()) {
            String itemKey = item.getKey();
            String itemValue = item.getValue();
            if (itemKey.toLowerCase().contains("card") && discountCard == null) {
                int cardId = this.getIntegerFromString(itemValue);
                discountCard = this.discountCardService.get(cardId);
                this.orderService.addDiscountCard(discountCard);
                continue;
            }
            int productId = this.getIntegerFromString(itemKey);
            int productCount = this.getIntegerFromString(itemValue);
            Product product = this.productService.get(productId);
            this.orderService.add(product, productCount);
        }
        return this.orderService.getReceipt();
    }

    @Override
    public List<Product> getProducts() {
        return this.productService.get();
    }

    private int getIntegerFromString(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException exception) {
            throw new ValidationException("Illegal param");
        }
    }
}
