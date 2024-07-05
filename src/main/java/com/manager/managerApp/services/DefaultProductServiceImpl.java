package com.manager.managerApp.services;

import com.manager.managerApp.entity.Product;
import com.manager.managerApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String details) {
        return productRepository.save(new Product(null, title, details));
    }

    @Override
    public Optional<Product> findProductById(Integer productId) {
        Product product = productRepository.findById(productId);
        return Optional.ofNullable(product);
    }
}
