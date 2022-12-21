package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.exception.ConversionException;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.repository.db_repository.api.IDbDiscountCardRepository;
import com.barkovsky.check_runner.repository.db_repository.entity.DiscountCardEntity;
import com.barkovsky.check_runner.service.api.IDiscountCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbDiscountCardService implements IDiscountCardService {

    private final IDbDiscountCardRepository discountCardRepository;
    private final ConversionService conversionService;
    private static final Logger logger = LoggerFactory.getLogger(DbDiscountCardService.class);

    public DbDiscountCardService(IDbDiscountCardRepository discountCardRepository, ConversionService conversionService) {
        this.discountCardRepository = discountCardRepository;
        this.conversionService = conversionService;
    }

    @Override
    public DiscountCard add(DiscountCard discountCard) {
        DiscountCardEntity discountCardEntity = this.conversionService.convert(discountCard, DiscountCardEntity.class);
        if (discountCardEntity == null) {
            throw new ConversionException("Essence conversion error");
        }
        discountCardEntity = this.discountCardRepository.save(discountCardEntity);
        logger.info("Discount card with id: {} was create", discountCardEntity.getId());
        return this.conversionService.convert(discountCardEntity, DiscountCard.class);
    }

    @Override
    public List<DiscountCard> get() {
        List<DiscountCardEntity> discountCardEntities = this.discountCardRepository.findAll();
        if (discountCardEntities.isEmpty()) {
            throw new EssenceNotFoundException("Discount cards don't exist");
        }
        List<DiscountCard> result = new ArrayList<>();
        discountCardEntities.forEach(discountCardEntity -> result.add(
                        this.conversionService.convert(discountCardEntity, DiscountCard.class)
                )
        );
        return result;
    }

    @Override
    public DiscountCard get(long id) {
        Optional<DiscountCardEntity> optionalDiscountCardEntity = this.discountCardRepository.findById(id);
        if (optionalDiscountCardEntity.isEmpty()) {
            throw new EssenceNotFoundException("Discount card with id: " + id + " doesn't exist");
        }
        return this.conversionService.convert(optionalDiscountCardEntity.get(), DiscountCard.class);
    }
}
