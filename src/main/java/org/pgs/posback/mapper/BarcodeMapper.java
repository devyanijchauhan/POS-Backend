package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Barcode.BarcodeRequestDTO;
import org.pgs.posback.DTO.Barcode.BarcodeResponseDTO;
import org.pgs.posback.model.BarcodeModel;

@Mapper
public interface BarcodeMapper {

    BarcodeMapper INSTANCE = Mappers.getMapper(BarcodeMapper.class);

    @Mapping(target = "id", source = "barcodeModel.id")
    @Mapping(target = "product", source = "barcodeModel.product")
    @Mapping(target = "barcodeImage", source = "barcodeModel.barcodeImage")
    @Mapping(target = "createdAt", source = "barcodeModel.createdAt")
    @Mapping(target = "updatedAt", source = "barcodeModel.updatedAt")
    BarcodeResponseDTO barcodeModelToBarcodeResponseDTO(BarcodeModel barcodeModel);

    @Mapping(target = "id", ignore = true)
    BarcodeModel barcodeRequestDTOToBarcodeModel(BarcodeRequestDTO barcodeRequestDTO);
}
