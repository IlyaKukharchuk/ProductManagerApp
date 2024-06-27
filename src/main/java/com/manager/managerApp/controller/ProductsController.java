package com.manager.managerApp.controller;

import com.manager.managerApp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductsController  {
    private final ProductService productService;


}
