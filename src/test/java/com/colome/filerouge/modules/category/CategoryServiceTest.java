package com.colome.filerouge.modules.category;

import com.colome.filerouge.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void saveCategory() {
        Category category = Category.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(categoryRepository.findByName("Tests fake name")).thenReturn(Optional.empty());
        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        Category savedCategory = categoryService.saveCategory(category);
        assertNotNull(savedCategory);
        assertEquals("Tests fake name", savedCategory.getName());
    }

    @Test
    void updateCategory() {
        Category category = Category.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Category categoryUpdated = Category.builder()
                .id(category.getId())
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(categoryRepository.findByName("Tests fake name")).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.save(categoryUpdated)).thenReturn(categoryUpdated);

        Category updatedCategory = categoryService.updateCategory(2L, categoryUpdated);
        assertNotNull(updatedCategory);
        assertEquals("Tests fake name", updatedCategory.getName());
    }

    @Test
    void getCategoryById() {
        Category category = Category.builder()
                .id(2L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        Category expectedCategory = categoryService.getCategoryById(2L);

        assertNotNull(expectedCategory);
        assertEquals("Tests fake name", expectedCategory.getName());
        assertEquals(true, expectedCategory.getStatus());
    }

    @Test
    void deleteCategoryById() {
        Category category = Category.builder()
                .id(1L)
                .name("Tests fake name")
                .status(true).build();

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        categoryService.deleteCategoryById(1L);
        Mockito.verify(categoryRepository).findById(1L);
        Mockito.verify(categoryRepository).deleteById(1L);
    }

    @Test
    void getAllCategories() {
        Category category = Category.builder()
                .name("Tests fake name")
                .status(true).build();

        List<Category> categoryList = List.of(category);
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);

        List<Category> expectedCategoryList = categoryService.getAllCategories();
        assertNotNull(expectedCategoryList);
        assertEquals(expectedCategoryList.size(), categoryList.size(), "Size should be 2");
        Mockito.verify(categoryRepository).findAll();
    }
}