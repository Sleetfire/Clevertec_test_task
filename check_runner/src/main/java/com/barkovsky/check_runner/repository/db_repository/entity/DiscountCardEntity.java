package com.barkovsky.check_runner.repository.db_repository.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity()
@Table(name = "discount_card", schema = "check_runner")
public class DiscountCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int discountPercent;

    public DiscountCardEntity(Long id, int discountPercent) {
        this.id = id;
        this.discountPercent = discountPercent;
    }

    public DiscountCardEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        DiscountCardEntity that = (DiscountCardEntity) o;
        return Objects.equals(id, that.id) && discountPercent == that.discountPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discountPercent);
    }

    @Override
    public String toString() {
        return "DiscountCardEntity{" +
                "id=" + id +
                ", discountPercent=" + discountPercent +
                '}';
    }

}
