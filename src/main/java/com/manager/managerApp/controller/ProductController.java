package com.manager.managerApp.controller;

import com.manager.managerApp.controller.payload.UpdateProductPayload;
import com.manager.managerApp.entity.Product;
import com.manager.managerApp.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
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

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementException(NoSuchElementException exception, Model model, HttpServletResponse response){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", exception.getMessage());
        return "errors/404";
    }
}
