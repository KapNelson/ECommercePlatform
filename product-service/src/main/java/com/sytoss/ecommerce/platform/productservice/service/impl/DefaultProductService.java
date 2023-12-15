package com.sytoss.ecommerce.platform.productservice.service.impl;

import com.sytoss.ecommerce.platform.productservice.model.Product;
import com.sytoss.ecommerce.platform.productservice.repository.ProductRepository;
import com.sytoss.ecommerce.platform.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts(Integer offset, Integer limit) {
        return productRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }

    @Override
    public List<Product> getProductsByCode(String code, Integer offset, Integer limit) {
        return productRepository.findAllByCode(code, PageRequest.of(offset, limit)).getContent();
    }

    @Override
    public Optional<Product> getProductByUid(UUID uid) {
        return productRepository.findById(uid);
    }

    @Override
    public void updateProduct(Product existingProduct, Product newProduct) {
        if (existingProduct == null || newProduct == null) {
            throw new IllegalArgumentException("The existingProduct or newProduct is null");
        }

        existingProduct.setCode(newProduct.getCode());
        existingProduct.setName(newProduct.getName());
        existingProduct.setDescription(newProduct.getDescription());
        existingProduct.setImage(newProduct.getImage());

        if (existingProduct.getPrice() == null) {
            existingProduct.setPrice(newProduct.getPrice());
        } else {
            existingProduct.getPrice().setAmount(newProduct.getPrice().getAmount());
            existingProduct.getPrice().setCurrency(newProduct.getPrice().getCurrency());
        }

        saveProduct(existingProduct);
    }

    @Override
    public void deleteProductByUid(UUID uid) {
        productRepository.deleteById(uid);
    }
}
