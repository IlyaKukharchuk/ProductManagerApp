package com.manager.managerApp.repository;

import com.manager.managerApp.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class InMemoryProductRepository {
     private final List<Product> productList = Collections.synchronizedList(new LinkedList<>());
}
