package com.barkovsky.check_runner.model.converter;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.repository.db_repository.entity.DiscountCardEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DiscountCardDtoToEntityConverter implements Converter<DiscountCard, DiscountCardEntity> {

    @Override
    public DiscountCardEntity convert(DiscountCard source) {
        return new DiscountCardEntity(source.getId(), source.getDiscountPercent());
    }
}
