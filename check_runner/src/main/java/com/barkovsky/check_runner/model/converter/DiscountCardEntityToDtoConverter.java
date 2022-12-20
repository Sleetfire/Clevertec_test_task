package com.barkovsky.check_runner.model.converter;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.repository.db_repository.entity.DiscountCardEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardEntityToDtoConverter implements Converter<DiscountCardEntity, DiscountCard> {

    @Override
    public DiscountCard convert(DiscountCardEntity source) {
        return new DiscountCard(source.getId(), source.getDiscountPercent());
    }
}
