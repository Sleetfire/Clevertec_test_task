package com.barkovsky.check_runner.model.converter;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.repository.db_repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDtoConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity source) {
        return Product.Builder.createBuilder()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setPrice(source.getPrice())
                .setDiscount(source.isDiscount())
                .build();
    }
}
