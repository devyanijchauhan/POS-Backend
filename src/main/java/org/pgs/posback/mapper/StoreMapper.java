package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Store.StoreRequestDTO;
import org.pgs.posback.DTO.Store.StoreResponseDTO;
import org.pgs.posback.model.StoreModel;

@Mapper
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "id", source = "storeModel.id")
    @Mapping(target = "name", source = "storeModel.name")
    @Mapping(target = "address", source = "storeModel.address")
    @Mapping(target = "contactNumber", source = "storeModel.contactNumber")
    @Mapping(target = "openingHours", source = "storeModel.openingHours")
    StoreResponseDTO storeModelToStoreResponseDTO(StoreModel storeModel);

    @Mapping(target = "id", ignore = true)
    StoreModel storeRequestDTOToStoreModel(StoreRequestDTO storeRequestDTO);
}
