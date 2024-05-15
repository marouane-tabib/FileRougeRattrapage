package com.colome.filerouge.modules.product;

import com.colome.filerouge.entity.Product;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;

import java.util.List;

public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        // check the product name
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ResourceNotFoundException("Product with name " + product.getName() + " already exists");
        }

        // save product
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
