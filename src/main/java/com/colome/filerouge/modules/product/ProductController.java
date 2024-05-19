package com.colome.filerouge.modules.product;

import com.colome.filerouge.entity.Product;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.product.dto.ProductRequestDTO;
import com.colome.filerouge.modules.product.dto.ProductResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductServiceInterface productService;

    public ProductController(ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<?> getProductService() {
        List<Product> products = productService.getAllProducts();

        if(products.isEmpty()) {
            return ResponseMessage.notFound("No product found");
        }else {
            List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
            for(Product product : products) {
                productResponseDTOS.add(ProductResponseDTO.fromProduct(product));
            }

            return ResponseMessage.ok(productResponseDTOS, "Success");
        }
    }

    //get product by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        // get the product by id
        Product product = productService.getProductById(id);

        // convert product to product response dto
        ProductResponseDTO productResponseDTO = ProductResponseDTO.fromProduct(product);

        // return response
        return ResponseMessage.ok(productResponseDTO, "Success");
    }

    // add product
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        // convert product request dto to product
        Product product = ProductRequestDTO.toProduct(productRequestDTO);

        // save the product
        product = productService.saveProduct(product);

        // convert product to product response dto
        ProductResponseDTO productResponseDTO = ProductResponseDTO.fromProduct(product);

        // return response
        return ResponseMessage.created(productResponseDTO, "Product added successfully");
    }

    // update product
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO productRequestDTO, @PathVariable Long id) {
        // convert product request dto to product
        Product product = ProductRequestDTO.toProduct(productRequestDTO);

        // update the product
        Product updatedProduct = productService.updateProduct(id, product);

        // convert product to product response dto
        ProductResponseDTO productResponseDTO = ProductResponseDTO.fromProduct(updatedProduct);

        // return response
        return ResponseMessage.ok(productResponseDTO, "Product updated successfully");
    }

    // delete product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        // delete the product
        productService.deleteProductById(id);

        // return response
        return ResponseMessage.ok(null,"Product deleted successfully");
    }
}
