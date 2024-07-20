package com.manager.manager_app.services;

import com.manager.manager_app.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Product createProduct(String title, String details);

    Optional<Product> findProductById(Integer productId);

    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
