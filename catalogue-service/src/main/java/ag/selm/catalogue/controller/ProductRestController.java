package ag.selm.catalogue.controller;

import ag.selm.catalogue.controller.payload.UpdateProductPayload;
import ag.selm.catalogue.entity.Product;
import ag.selm.catalogue.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products/{productId:\\d+}")
public class ProductRestController {
    private final ProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product getProduct(@PathVariable("productId") int productId){
        return this.productService.findProductById(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product){
        return product;
    }

    @PatchMapping
    public ResponseEntity<Object> editProduct(@ModelAttribute("product") Product product,
                                         @Valid UpdateProductPayload payload, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return null;
        }{
            productService.updateProduct(product.getId(), payload.title(), payload.details());
            return ResponseEntity.noContent().build();
        }
    }
}
