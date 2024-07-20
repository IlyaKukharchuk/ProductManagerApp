package com.manager.manager_app.repository;

import com.manager.manager_app.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    Product findById(int productId);

    void deleteById(Integer id);
}
