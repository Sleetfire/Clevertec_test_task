package com.barkovsky.check_runner.repository.memory_repository.api;

import com.barkovsky.check_runner.model.dto.Product;

import java.util.List;

public interface IProductRepository {

    Product add(Product product);

    List<Product> get();

    Product get(long id);

}
