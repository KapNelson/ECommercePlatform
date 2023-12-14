package com.sytoss.ecommerce.platform.productservice.controller;

import com.sytoss.ecommerce.platform.productservice.model.Product;
import com.sytoss.ecommerce.platform.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
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
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);

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
        PageRequest pageRequest = PageRequest.of(offset, limit);
        return StringUtils.isEmpty(code) ? productRepository.findAll(pageRequest).getContent() : productRepository.findAllByCode(code, pageRequest).getContent();
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Product> getProductByUid(@PathVariable UUID uid) {
        return productRepository.findById(uid).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
