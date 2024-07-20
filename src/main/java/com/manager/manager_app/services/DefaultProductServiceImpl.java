package com.manager.manager_app.services;

import com.manager.manager_app.entity.Product;
import com.manager.manager_app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        return Optional.ofNullable(productRepository.findById(productId));
    }
    @Override
    public void updateProduct(Integer id, String title, String details) {
        Optional<Product> productOptional = Optional.ofNullable(this.productRepository.findById(id));
        productOptional.ifPresentOrElse(product -> {
            product.setTitle(title);
            product.setDetails(details);
        }, () -> {
            throw new NoSuchElementException("Product not found");
        });
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
