package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Category.CategoryRequestDTO;
import org.pgs.posback.DTO.Category.CategoryResponseDTO;
import org.pgs.posback.mapper.CategoryMapper;
import org.pgs.posback.model.CategoryModel;
import org.pgs.posback.repository.CategoryRepository;
import org.pgs.posback.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper.INSTANCE::categoryModelToCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper.INSTANCE::categoryModelToCategoryResponseDTO)
                .orElse(null);
    }

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        CategoryModel category = CategoryMapper.INSTANCE.categoryRequestDTOToCategoryModel(categoryRequestDTO);
        CategoryModel savedCategory = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryModelToCategoryResponseDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setName(categoryRequestDTO.getName());
                    return CategoryMapper.INSTANCE.categoryModelToCategoryResponseDTO(categoryRepository.save(category));
                })
                .orElse(null);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
