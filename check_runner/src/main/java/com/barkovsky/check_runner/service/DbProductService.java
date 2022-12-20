package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.ConversionException;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.repository.db_repository.api.IDbProductRepository;
import com.barkovsky.check_runner.repository.db_repository.entity.ProductEntity;
import com.barkovsky.check_runner.service.api.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbProductService implements IProductService {

    private final IDbProductRepository productRepository;
    private final ConversionService conversionService;
    private static final Logger logger = LoggerFactory.getLogger(DbProductService.class);

    public DbProductService(IDbProductRepository productRepository, ConversionService conversionService) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Product add(Product product) {
        ProductEntity productEntity = this.conversionService.convert(product, ProductEntity.class);
        if (productEntity == null) {
            throw new ConversionException("Essence conversion error");
        }
        productEntity = this.productRepository.save(productEntity);
        logger.info("Essence with id: {} was create", productEntity.getId());
        return this.conversionService.convert(productEntity, Product.class);
    }

    @Override
    public List<Product> get() {
        List<ProductEntity> productEntities = this.productRepository.findAll();
        if (productEntities.isEmpty()) {
            throw new EssenceNotFoundException("Essences don't exists");
        }
        List<Product> result = new ArrayList<>();
        productEntities.forEach(productEntity -> result.add(this.conversionService.convert(productEntity, Product.class)));
        return result;
    }

    @Override
    public Product get(long id) {
        Optional<ProductEntity> optionalProductEntity = this.productRepository.findById(id);
        if (optionalProductEntity.isEmpty()) {
            throw new EssenceNotFoundException("Essence with id: " + id + " doesn't exist");
        }
        return this.conversionService.convert(optionalProductEntity.get(), Product.class);
    }
}
