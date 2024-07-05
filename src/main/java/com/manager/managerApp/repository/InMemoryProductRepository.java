package com.manager.managerApp.repository;

import com.manager.managerApp.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository{
     private final List<Product> productList = Collections.synchronizedList(new LinkedList<>());

     @Override
     public List<Product> findAll() {
          return Collections.unmodifiableList(productList);
     }

     @Override
     public Product save(Product product) {
          product.setId(this.productList.stream()
                  .max(Comparator.comparingInt(Product :: getId))
                  .map(Product :: getId)
                  .orElse(0)+1);
          this.productList.add(product);
          return product;
     }
}
