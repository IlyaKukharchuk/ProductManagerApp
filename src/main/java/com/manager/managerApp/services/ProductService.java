package com.manager.managerApp.services;

import com.manager.managerApp.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
}
