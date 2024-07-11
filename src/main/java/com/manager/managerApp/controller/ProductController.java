package com.manager.managerApp.controller;

import com.manager.managerApp.controller.payload.NewProductPayload;
import com.manager.managerApp.controller.payload.UpdateProductPayload;
import com.manager.managerApp.entity.Product;
import com.manager.managerApp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {
    private final ProductService productService;
    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProduct(){
        return "catalogue/products/product";
    }

    @GetMapping("edit_product")
    public String getProductEditPage(){
        return "catalogue/products/edit_product";
    }

    @PostMapping("edit_product")
    public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayload payload){
        this.productService.updateProduct(product.getId(), payload.title(), payload.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }
}
