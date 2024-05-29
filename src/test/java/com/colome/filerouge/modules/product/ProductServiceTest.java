package com.colome.filerouge.modules.product;

import com.colome.filerouge.entity.Product;
import com.colome.filerouge.modules.category.CategoryRepository;
import com.colome.filerouge.modules.category.CategoryService;
import com.colome.filerouge.modules.color.ColorRepository;
import com.colome.filerouge.modules.color.ColorService;
import com.colome.filerouge.modules.material.MaterialRepository;
import com.colome.filerouge.modules.material.MaterialService;
import com.colome.filerouge.modules.room.RoomRepository;
import com.colome.filerouge.modules.room.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductRepository productRepository;
    private ProductService productService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private RoomService roomService;
    private RoomRepository roomRepository;
    private MaterialService materialService;
    private MaterialRepository materialRepository;
    private ColorService colorService;
    private ColorRepository colorRepository;

    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
        roomRepository = Mockito.mock(RoomRepository.class);
        roomService = new RoomService(roomRepository);
        materialRepository = Mockito.mock(MaterialRepository.class);
        materialService = new MaterialService(materialRepository);
        colorRepository = Mockito.mock(ColorRepository.class);
        colorService = new ColorService(colorRepository);

        productService = new ProductService(productRepository, categoryService, roomService, materialService, colorService);
    }

    @Test
    void saveProduct() {
        Product product = Product.builder()
                .id(2L)
                .name("Tests fake name")
                .description("Its a fake description")
                .price(300D)
                .status(true).build();

        Mockito.when(productRepository.findByName("Tests fake name")).thenReturn(Optional.empty());
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);
        assertNotNull(savedProduct);
        assertEquals("Tests fake name", savedProduct.getName());
        assertEquals("Its a fake description", savedProduct.getDescription());
        assertEquals(300D, savedProduct.getPrice());
    }

    @Test
    void updateProduct() {
        Product product = Product.builder()
                .id(2L)
                .name("Tests fake name")
                .description("Its a fake description")
                .price(300D)
                .status(true).build();

        Product productUpdated = Product.builder()
                .id(product.getId())
                .name("Tests fake name")
                .description("Its a fake description -u")
                .price(600D)
                .status(true).build();

        Mockito.when(productRepository.findByName("Tests fake name")).thenReturn(Optional.of(product));
        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(productUpdated)).thenReturn(productUpdated);

        Product updatedProduct = productService.updateProduct(2L, productUpdated);
        assertNotNull(updatedProduct);
        assertEquals("Tests fake name", updatedProduct.getName());
        assertEquals("Its a fake description -u", updatedProduct.getDescription());
        assertEquals(600D, updatedProduct.getPrice());
    }

    @Test
    void getProductById() {
        Product product = Product.builder()
                .id(2L)
                .name("Tests fake name")
                .description("Its a fake description")
                .price(300D)
                .status(true).build();

        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(product));
        Product expectedProduct = productService.getProductById(2L);

        assertNotNull(expectedProduct);
        assertEquals("Tests fake name", expectedProduct.getName());
        assertEquals("Its a fake description", expectedProduct.getDescription());
        assertEquals(300D, expectedProduct.getPrice());
        assertEquals(true, expectedProduct.getStatus());
    }

    @Test
    void deleteProductById() {
        Product product = Product.builder()
                .id(2L)
                .name("Tests fake name")
                .description("Its a fake description")
                .price(300D)
                .status(true).build();

        Mockito.when(productRepository.findById(2L)).thenReturn(Optional.of(product));
        productService.deleteProductById(2L);
        Mockito.verify(productRepository).findById(2L);
        Mockito.verify(productRepository).deleteById(2L);
    }

    @Test
    void getAllProducts() {
        Product product = Product.builder()
                .name("Tests fake name")
                .description("Its a fake description")
                .price(300D)
                .status(true).build();

        List<Product> productList = List.of(product);
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<Product> expectedProductList = productService.getAllProducts();
        assertNotNull(expectedProductList);
        assertEquals(expectedProductList.size(), productList.size(), "Size should be 2");
        Mockito.verify(productRepository).findAll();
    }
}