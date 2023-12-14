package com.sytoss.ecommerce.platform.productservice.repository;

import com.sytoss.ecommerce.platform.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAllByCode(String code, Pageable pageable);
}
