package com.colome.filerouge.modules.product;

import com.colome.filerouge.entity.Product;

import java.util.List;

public interface ProductServiceInterface {
    Product saveProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product getProductById(Long id);

    void deleteProductById(Long id);
    
    List<Product> getAllProducts();
}
