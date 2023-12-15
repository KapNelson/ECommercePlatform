package com.sytoss.ecommerce.platform.productservice.service;

import com.sytoss.ecommerce.platform.productservice.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing products.
 */
public interface ProductService {

    /**
     * Saves a new product or updates an existing one.
     *
     * @param product The product to be saved or updated.
     * @return The saved or updated product.
     */
    Product saveProduct(Product product);

    /**
     * Retrieves a list of products with pagination.
     *
     * @param offset The starting index of the result set (pagination offset).
     * @param limit  The maximum number of products to retrieve (pagination limit).
     * @return The list of products within the specified range.
     */
    List<Product> getProducts(Integer offset, Integer limit);

    /**
     * Retrieves a list of products with a specific code and pagination.
     *
     * @param code   The product code to filter products by.
     * @param offset The starting index of the result set (pagination offset).
     * @param limit  The maximum number of products to retrieve (pagination limit).
     * @return The list of products with the specified code within the specified range.
     */
    List<Product> getProductsByCode(String code, Integer offset, Integer limit);

    /**
     * Retrieves a product by its unique identifier (UID).
     *
     * @param uid The unique identifier of the product.
     * @return An {@link Optional} containing the product with the specified UID, or empty if not found.
     */
    Optional<Product> getProductByUid(UUID uid);

    /**
     * Updates an existing product with new information.
     *
     * @param existingProduct The existing product to be updated.
     * @param newProduct      The new product information to be applied.
     * @throws IllegalArgumentException If the existingProduct or newProduct is null.
     */
    void updateProduct(Product existingProduct, Product newProduct);

    /**
     * Deletes a product by its unique identifier (UID).
     *
     * @param uid The unique identifier of the product to be deleted.
     */
    void deleteProductByUid(UUID uid);
}
