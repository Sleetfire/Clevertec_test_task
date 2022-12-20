package com.barkovsky.check_runner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DiscountCard {
    @JsonProperty("id")
    private long id;
    @JsonProperty("discount_percent")
    private int discountPercent;

    public DiscountCard(long id, int discountPercent) {
        this.id = id;
        this.discountPercent = discountPercent;
    }

    public DiscountCard() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return id == that.id && discountPercent == that.discountPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discountPercent);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", discountPercent=" + discountPercent +
                '}';
    }

    public static class Builder {
        private long id;
        private int discountPercent;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
            return this;
        }

        public DiscountCard build() {
            return new DiscountCard(this.id, this.discountPercent);
        }
    }

}
