package org.pgs.posback.service;

import org.pgs.posback.DTO.Category.CategoryRequestDTO;
import org.pgs.posback.DTO.Category.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO getCategoryById(Long id);
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(Long categoryId);
}
