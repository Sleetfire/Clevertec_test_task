package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.model.dto.OrderProduct;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.service.api.IOrderService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private final List<OrderProduct> products = new ArrayList<>();
    private DiscountCard discountCard;

    @Override
    public void add(Product product, int count) {
        products.add(new OrderProduct(product, count));
    }

    @Override
    public List<OrderProduct> getProducts() {
        return List.copyOf(this.products);
    }

    @Override
    public void addDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    @Override
    public String getReceipt() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%27s", "CASH RECEIPT")).append("\n");
        builder.append(String.format("%28s", "SUPERMARKET 42")).append("\n");
        builder.append(String.format("%34s", "12, MILKY WAY GALAXY, Earth")).append("\n");
        builder.append(String.format("%29s", "Tel: 123-456-789")).append("\n").append("\n");
        builder.append(String.format("%-20s%22s", "CASHIER: #1570", "DATE: " +
                LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)))).append("\n");
        builder.append(String.format("%40s", "TIME: " +
                LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))).append("\n");
        builder.append("------------------------------------------").append("\n");
        builder.append(String.format("%-4s", "QTY")).append(" ").append(String.format("%-21s", "DESCRIPTION"))
                .append(String.format("%5s", "PRICE"))
                .append(" ")
                .append(String.format("%5s", "TOTAL"))
                .append("\n");

        for (OrderProduct product : this.products) {
            builder.append(product.toString())
                    .append("\n");
        }

        builder.append("\n")
                .append("==========================================").append("\n")
                .append("\n");
        if (this.getDiscount() != 0) {
            builder.append(String.format("%8s", "AMOUNT: ")).append(String.format("%19s%.2f", "$", this.getSum()))
                    .append("\n")
                    .append(String.format("%10s", "DISCOUNT: ")).append(String.format("%17s%.2f", "-$", this.getDiscount()))
                    .append("\n");
        }
        builder.append(String.format("%7s", "TOTAL: ")).append(String.format("%20s%.2f", "$", this.getSumForPay()));
        return builder.toString();
    }

    @Override
    public double getSumForPay() {
        double sum = getSum();
        return calcSumWithDiscount(sum, calcDiscount(sum, this.discountCard));
    }

    @Override
    public double getSum() {
        return products.stream()
                .mapToDouble(product -> product.getSum().doubleValue())
                .sum();
    }

    @Override
    public double getDiscount() {
        return getSum() - getSumForPay();
    }

    private double calcDiscount(double sum, DiscountCard discountCard) {
        double discountSum = 0;
        if (discountCard != null) {
            discountSum = calcDiscount(sum, discountCard.getDiscountPercent());
        }
        for (OrderProduct product : products) {
            discountSum += product.getAdditionalDiscount();
        }
        return discountSum;
    }

    private double calcDiscount(double sum, int discount) {
        sum = sum * (discount * 1.0 / 100);
        return sum;
    }

    private double calcSumWithDiscount(double sum, double discountSum) {
        if (Double.compare(discountSum, 0) > 0) {
            return sum - discountSum;
        }
        return sum;
    }

}
