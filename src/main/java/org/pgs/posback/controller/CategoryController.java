package org.pgs.posback.controller;

import org.pgs.posback.model.CategoryModel;
import org.pgs.posback.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable("id") Long id) {
        Optional<CategoryModel> categoryData = categoryRepository.findById(id);
        return categoryData.map(categoryModel -> new ResponseEntity<>(categoryModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel categoryModel) {
        try {
            CategoryModel createdCategory = categoryRepository.save(categoryModel);
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{categoryid}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable("categoryid") Long categoryid, @RequestBody CategoryModel categoryModel) {
        Optional<CategoryModel> categoryData = categoryRepository.findById(categoryid);

        if (categoryData.isPresent()) {
            CategoryModel updatedCategory = categoryData.get();
            updatedCategory.setName(categoryModel.getName());
            updatedCategory.setCreatedAt(categoryModel.getCreatedAt());
            updatedCategory.setUpdatedAt(categoryModel.getUpdatedAt());
            return new ResponseEntity<>(categoryRepository.save(updatedCategory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idc}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("idc") Long idc) {
        try {
            categoryRepository.deleteById(idc);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
