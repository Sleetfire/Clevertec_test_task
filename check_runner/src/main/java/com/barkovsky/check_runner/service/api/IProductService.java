package com.barkovsky.check_runner.service.api;

import com.barkovsky.check_runner.model.dto.Product;

import java.util.List;

public interface IProductService {

    Product add(Product product);

    List<Product> get();

    Product get(long id);

}
