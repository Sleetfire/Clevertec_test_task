package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.OrderProduct;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IOrderService;
import com.barkovsky.check_runner.util.ReadWriteFileUtil;

import java.util.List;

public class FileOrderDecoratorService implements IOrderService {

    private final IOrderService orderService;

    public FileOrderDecoratorService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void add(Product product, int count) {
        this.orderService.add(product, count);
    }

    @Override
    public List<OrderProduct> getProducts() {
        return this.orderService.getProducts();
    }

    @Override
    public void addDiscountCard(DiscountCard discountCard) {
        this.orderService.addDiscountCard(discountCard);
    }

    @Override
    public String getReceipt() {
        String receipt = this.orderService.getReceipt();
        ReadWriteFileUtil.writeStringInFile(receipt, ".txt");
        return receipt;
    }

    @Override
    public double getSumForPay() {
        return this.orderService.getSumForPay();
    }

    @Override
    public double getSum() {
        return this.orderService.getSum();
    }

    @Override
    public double getDiscount() {
        return this.orderService.getDiscount();
    }

}
