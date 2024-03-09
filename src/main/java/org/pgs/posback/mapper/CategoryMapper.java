package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Category.CategoryRequestDTO;
import org.pgs.posback.DTO.Category.CategoryResponseDTO;
import org.pgs.posback.model.CategoryModel;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", source = "categoryModel.id")
    @Mapping(target = "name", source = "categoryModel.name")
    @Mapping(target = "createdAt", source = "categoryModel.createdAt")
    @Mapping(target = "updatedAt", source = "categoryModel.updatedAt")
    CategoryResponseDTO categoryModelToCategoryResponseDTO(CategoryModel categoryModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CategoryModel categoryRequestDTOToCategoryModel(CategoryRequestDTO categoryRequestDTO);
}
