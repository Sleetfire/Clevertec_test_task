package com.barkovsky.check_runner.repository.memory_repository;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.repository.memory_repository.api.IProductRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MemoryProductRepository implements IProductRepository {

    private final Map<Long, Product> products = new TreeMap<>();
    private static final IProductRepository instance = new MemoryProductRepository();

    private MemoryProductRepository() {
    }

    @Override
    public Product add(Product product) {
        return products.put(product.getId(), product);
    }

    @Override
    public List<Product> get() {
        return List.copyOf(products.values());
    }

    @Override
    public Product get(long id) {
        return this.products.get(id);
    }

    public static IProductRepository getInstance() {
        return instance;
    }
}
