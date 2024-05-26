package com.colome.filerouge.modules.product;

import com.colome.filerouge.entity.Product;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        // check the product name
        if (productRepository.findByName(product.getName()).isPresent() && !productRepository.findByName(product.getName()).get().getId().equals(id)) {
            throw new ResourceNotFoundException("Product with name " + product.getName() + " already exists");
        }

        // get product by id
        Product productToUpdate = this.getProductById(id);

        // set sku, slug, category, brand
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());

        // save product
        return productRepository.save(productToUpdate);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product with id " + id + " not found")
                );
    }

    @Override
    public void deleteProductById(Long id) {
        this.getProductById(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
