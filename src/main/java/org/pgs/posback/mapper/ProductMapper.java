package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Product.ProductRequestDTO;
import org.pgs.posback.DTO.Product.ProductResponseDTO;
import org.pgs.posback.model.ProductModel;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductModel productRequestDTOToProductModel(ProductRequestDTO productRequestDTO);

    ProductResponseDTO productModelToProductResponseDTO(ProductModel productModel);

    List<ProductResponseDTO> productModelsToProductResponseDTOList(List<ProductModel> productModels);
}
