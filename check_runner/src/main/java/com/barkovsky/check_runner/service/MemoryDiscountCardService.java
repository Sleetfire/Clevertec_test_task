package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.repository.memory_repository.MemoryDiscountCardRepository;
import com.barkovsky.check_runner.repository.memory_repository.api.IDiscountCardRepository;
import com.barkovsky.check_runner.service.api.IDiscountCardService;

import java.util.List;

public class MemoryDiscountCardService implements IDiscountCardService {

    private static final IDiscountCardService instance = new MemoryDiscountCardService();
    private final IDiscountCardRepository discountCardRepository = MemoryDiscountCardRepository.getInstance();

    @Override
    public DiscountCard add(DiscountCard discountCard) {
        return this.discountCardRepository.add(discountCard);
    }

    @Override
    public List<DiscountCard> get() {
        List<DiscountCard> discountCards = this.discountCardRepository.get();
        if (discountCards.isEmpty()) {
            System.err.println("Empty discount card's storage");
        }
        return discountCards;
    }

    @Override
    public DiscountCard get(long id) {
        DiscountCard discountCard = this.discountCardRepository.get(id);
        if (discountCard == null) {
            throw new EssenceNotFoundException("Discount card with id: " + id + " doesn't exist");
        }
        return discountCard;
    }

    public static IDiscountCardService getInstance() {
        return instance;
    }
}
