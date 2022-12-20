package com.barkovsky.check_runner.model.converter;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.repository.db_repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToEntityConverter implements Converter<Product, ProductEntity> {

    @Override
    public ProductEntity convert(Product source) {
        return ProductEntity.Builder.createBuilder()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setPrice(source.getPrice())
                .setDiscount(source.isDiscount())
                .build();
    }
}
