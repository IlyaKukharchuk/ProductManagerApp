package com.manager.managerApp.services;

import com.manager.managerApp.entity.Product;
import com.manager.managerApp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;


    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }
}
