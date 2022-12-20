package com.barkovsky.check_runner.service.api;

import com.barkovsky.check_runner.model.dto.DiscountCard;

import java.util.List;

public interface IDiscountCardService {

    DiscountCard add(DiscountCard discountCard);

    List<DiscountCard> get();

    DiscountCard get(long id);

}
