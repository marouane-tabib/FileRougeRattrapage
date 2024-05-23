package com.colome.filerouge.modules.category;

import com.colome.filerouge.entity.Category;
import com.colome.filerouge.handlers.response.ResponseMessage;
import com.colome.filerouge.modules.category.dto.CategoryRequestDTO;
import com.colome.filerouge.modules.category.dto.CategoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    private final CategoryServiceInterface categoryService;

    public CategoryController(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<?> getCategoryService() {
        List<Category> categories = categoryService.getAllCategories();

        if(categories.isEmpty()) {
            return ResponseMessage.notFound("No category found");
        }else {
            List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
            for(Category category : categories) {
                categoryResponseDTOS.add(CategoryResponseDTO.fromCategory(category));
            }

            return ResponseMessage.ok(categoryResponseDTOS, "Success");
        }
    }

    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        // get the category by id
        Category category = categoryService.getCategoryById(id);

        // convert category to category response dto
        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.fromCategory(category);

        // return response
        return ResponseMessage.ok(categoryResponseDTO, "Success");
    }

    // add category
    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        // convert category request dto to category
        Category category = CategoryRequestDTO.toCategory(categoryRequestDTO);

        // save the category
        category = categoryService.saveCategory(category);

        // convert category to category response dto
        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.fromCategory(category);

        // return response
        return ResponseMessage.created(categoryResponseDTO, "Category added successfully");
    }

    // update category
    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, @PathVariable Long id) {
        // convert category request dto to category
        Category category = CategoryRequestDTO.toCategory(categoryRequestDTO);

        // update the category
        Category updatedCategory = categoryService.updateCategory(id, category);

        // convert category to category response dto
        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.fromCategory(updatedCategory);

        // return response
        return ResponseMessage.ok(categoryResponseDTO, "Category updated successfully");
    }

    // delete category
    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        // delete the category
        categoryService.deleteCategoryById(id);

        // return response
        return ResponseMessage.ok(null,"Category deleted successfully");
    }
}
