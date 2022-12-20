package com.barkovsky.check_runner.repository.memory_repository;

import com.barkovsky.check_runner.model.dto.DiscountCard;
import com.barkovsky.check_runner.repository.memory_repository.api.IDiscountCardRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
public class MemoryDiscountCardRepository implements IDiscountCardRepository {
    private final Map<Long, DiscountCard> discountCardMap = new TreeMap<>();
    private static final IDiscountCardRepository instance = new MemoryDiscountCardRepository();
    private MemoryDiscountCardRepository() {
    }

    @Override
    public DiscountCard add(DiscountCard discountCard) {
        return discountCardMap.put(discountCard.getId(), discountCard);
    }

    @Override
    public List<DiscountCard> get() {
        return List.copyOf(discountCardMap.values());
    }

    @Override
    public DiscountCard get(long id) {
        return this.discountCardMap.get(id);
    }

    public static IDiscountCardRepository getInstance() {
        return instance;
    }
}
