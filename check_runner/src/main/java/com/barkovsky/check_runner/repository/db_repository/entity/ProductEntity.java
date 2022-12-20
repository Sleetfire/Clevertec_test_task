package com.barkovsky.check_runner.repository.db_repository.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity()
@Table(name = "product", schema = "check_runner")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal price;
    @Column(name = "is_discount")
    private boolean isDiscount;

    public ProductEntity(Long id, String description, BigDecimal price, boolean isDiscount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.isDiscount = isDiscount;
    }

    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return isDiscount == that.isDiscount
                && Objects.equals(id, that.id)
                && Objects.equals(description, that.description)
                && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, isDiscount);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", isDiscount=" + isDiscount +
                '}';
    }

    public static class Builder {
        private Long id;
        private String description;
        private BigDecimal price;
        private boolean isDiscount;

        private Builder() {

        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(Long id) {
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

        public ProductEntity build() {
            return new ProductEntity(this.id, this.description, this.price, this.isDiscount);
        }
    }
}
