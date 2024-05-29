package com.colome.filerouge.modules.product;

import com.colome.filerouge.entity.*;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import com.colome.filerouge.modules.category.CategoryServiceInterface;
import com.colome.filerouge.modules.color.ColorServiceInterface;
import com.colome.filerouge.modules.material.MaterialServiceInterface;
import com.colome.filerouge.modules.room.RoomServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;
    private final CategoryServiceInterface categoryService;
    private final RoomServiceInterface roomService;
    private final MaterialServiceInterface materialService;
    private final ColorServiceInterface colorService;

    public ProductService(ProductRepository productRepository, CategoryServiceInterface categoryService, RoomServiceInterface roomService, MaterialServiceInterface materialService, ColorServiceInterface colorService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.roomService = roomService;
        this.materialService = materialService;
        this.colorService = colorService;
    }

    @Override
    public Product saveProduct(Product product) {
        // check the product name
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ResourceNotFoundException("Product with name " + product.getName() + " already exists");
        }

        product = this.setRelations(product);

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
        
        product = this.setRelations(product);

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

    public Product setRelations(@NotNull Product product) {
        Category category = categoryService.getCategoryById(product.getCategory().getId());
        product.setCategory(category);
        Room room = roomService.getRoomById(product.getCategory().getId());
        product.setRoom(room);
        Material material = materialService.getMaterialById(product.getCategory().getId());
        product.setMaterial(material);
        Color color = colorService.getColorById(product.getCategory().getId());
        product.setColor(color);

        return product;
    }
}
