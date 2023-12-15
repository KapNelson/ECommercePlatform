package com.sytoss.ecommerce.platform.productservice.repository;

import com.sytoss.ecommerce.platform.productservice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAllByCode(String code, Pageable pageable);
}
