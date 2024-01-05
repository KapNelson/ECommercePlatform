package com.sytoss.ecommerce.platform.productservice.controller;

import com.sytoss.ecommerce.platform.productservice.model.Product;
import com.sytoss.ecommerce.platform.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(savedProduct.getUid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String code,
                                     @RequestParam(required = false, defaultValue = "0") Integer offset,
                                     @RequestParam(required = false, defaultValue = "50") Integer limit) {
        return StringUtils.isEmpty(code) ? productService.getProducts(offset, limit) : productService.getProductsByCode(code, offset, limit);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Product> getProductByUid(@PathVariable UUID uid) {
        return productService.getProductByUid(uid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{uid}")
    public ResponseEntity<Void> updateProductByUid(@PathVariable UUID uid, @RequestBody Product product) {
        Optional<Product> optionalProduct = productService.getProductByUid(uid);

        if (optionalProduct.isPresent()) {
            productService.updateProduct(optionalProduct.get(), product);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> deleteProductByUid(@PathVariable UUID uid) {
        productService.deleteProductByUid(uid);
        return ResponseEntity.noContent().build();
    }
}
