package com.colome.filerouge.modules.category;

import com.colome.filerouge.entity.Category;
import com.colome.filerouge.handlers.exceptionHandler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        // check the category name
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new ResourceNotFoundException("Category with name " + category.getName() + " already exists");
        }

        // save category
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        // check the category name
        if (categoryRepository.findByName(category.getName()).isPresent() && !categoryRepository.findByName(category.getName()).get().getId().equals(id)) {
            throw new ResourceNotFoundException("Category with name " + category.getName() + " already exists");
        }

        // get category by id
        Category categoryToUpdate = this.getCategoryById(id);

        // set sku, slug, category, brand
        categoryToUpdate.setName(category.getName());

        // save category
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category with id " + id + " not found")
                );
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = this.getCategoryById(id);
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
