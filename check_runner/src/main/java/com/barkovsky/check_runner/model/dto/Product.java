package com.barkovsky.check_runner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    @JsonProperty("id")
    private long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("discount")
    private boolean discount;

    public Product(long id, String description, BigDecimal price, boolean discount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id
                && discount == product.discount
                && Objects.equals(description, product.description)
                && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, discount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isDiscount=" + discount +
                '}';
    }

    public static class Builder {
        private long id;
        private String description;
        private BigDecimal price;
        private boolean isDiscount;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setDiscount(boolean discount) {
            isDiscount = discount;
            return this;
        }

        public Product build() {
            return new Product(this.id, this.description, this.price, this.isDiscount);
        }
    }
}
