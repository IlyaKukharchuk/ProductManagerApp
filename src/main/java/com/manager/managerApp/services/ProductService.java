package com.manager.managerApp.services;

import com.manager.managerApp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Product createProduct(String title, String details);

    Optional<Product> findProductById(int productId);
}
