package com.barkovsky.check_runner.service;

import com.barkovsky.check_runner.model.dto.Product;
import com.barkovsky.check_runner.exception.EssenceNotFoundException;
import com.barkovsky.check_runner.repository.memory_repository.MemoryProductRepository;
import com.barkovsky.check_runner.repository.memory_repository.api.IProductRepository;
import com.barkovsky.check_runner.service.api.IProductService;

import java.util.Collections;
import java.util.List;

public class MemoryProductService implements IProductService {

    private static final IProductService instance = new MemoryProductService();
    private final IProductRepository productRepository = MemoryProductRepository.getInstance();
    private MemoryProductService() {
    }

    @Override
    public Product add(Product product) {
        return this.productRepository.add(product);
    }

    @Override
    public List<Product> get() {
        List<Product> products = this.productRepository.get();
        if (products.isEmpty()) {
            System.err.println("Empty product's storage");
            return Collections.emptyList();
        }
        return products;
    }

    @Override
    public Product get(long id) {
        Product product = this.productRepository.get(id);
        if (product == null) {
            throw new EssenceNotFoundException("Product with id: " + id + " doesn't exist");
        }
        return product;
    }

    public static IProductService getInstance() {
        return instance;
    }
}
