package com.colome.filerouge.modules.category;

import com.colome.filerouge.entity.Category;
import com.colome.filerouge.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {
    Category saveCategory(Category category);

    Category updateCategory(Long id, Category category);

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);
    
    List<Category> getAllCategories();
}
