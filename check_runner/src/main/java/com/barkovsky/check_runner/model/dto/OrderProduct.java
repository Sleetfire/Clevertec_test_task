package com.barkovsky.check_runner.model.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderProduct {
    private Product product;
    private int count;

    public OrderProduct(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public OrderProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return product.getPrice().multiply(BigDecimal.valueOf(count));
    }

    public double getAdditionalDiscount() {
        if (product.isDiscount() && count > 5) {
            return getSum().doubleValue() * 0.1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return count == that.count && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, count);
    }

    @Override
    public String toString() {
        return String.format("%-4d", count) + " " + String.format("%-21s", product.getDescription()) +
                String.format("%-5s", "$" + product.getPrice())+ String.format("%2s%.2f", "$", getSum().doubleValue());
    }
}
