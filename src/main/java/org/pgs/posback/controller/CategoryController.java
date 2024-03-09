package org.pgs.posback.controller;

import org.pgs.posback.DTO.Category.CategoryRequestDTO;
import org.pgs.posback.DTO.Category.CategoryResponseDTO;
import org.pgs.posback.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO createdCategory = categoryService.createCategory(categoryRequestDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponseDTO updatedCategory = categoryService.updateCategory(categoryId, categoryRequestDTO);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long cId) {
        categoryService.deleteCategory(cId);
        return ResponseEntity.noContent().build();
    }
}
