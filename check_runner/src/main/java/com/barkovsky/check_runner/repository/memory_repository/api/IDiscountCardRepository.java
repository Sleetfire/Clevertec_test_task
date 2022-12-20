package com.barkovsky.check_runner.repository.memory_repository.api;

import com.barkovsky.check_runner.model.dto.DiscountCard;

import java.util.List;

public interface IDiscountCardRepository {

    DiscountCard add(DiscountCard discountCard);

    List<DiscountCard> get();

    DiscountCard get(long id);

}
