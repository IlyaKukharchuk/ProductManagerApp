package com.manager.managerApp.controller;

import com.manager.managerApp.controller.payload.NewProductPayload;
import com.manager.managerApp.entity.Product;
import com.manager.managerApp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController  {
    private final ProductService productService;

    @GetMapping("list")
    public String getProductsList(Model model){
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("new_product")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload){
        Product newProduct = this.productService.createProduct(payload.title(), payload.details());
        return "redirect:/catalogue/products/%d".formatted(newProduct.getId());
    }

    @GetMapping("{productId:\\d+}")
    public String getProduct(@PathVariable("productId") Integer productId, Model model){
        model.addAttribute("product", this.productService.findProductById(productId).orElseThrow(() -> new NoSuchElementException("product not found")));
        return "catalogue/products/product";
    }
}
