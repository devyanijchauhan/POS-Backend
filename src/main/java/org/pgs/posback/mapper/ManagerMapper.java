package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Manager.ManagerRequestDTO;
import org.pgs.posback.DTO.Manager.ManagerResponseDTO;
import org.pgs.posback.model.ManagerModel;

@Mapper
public interface ManagerMapper {

    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);

    @Mapping(target = "id", source = "managerModel.id")
    @Mapping(target = "name", source = "managerModel.name")
    @Mapping(target = "role", source = "managerModel.role")
    @Mapping(target = "account", source = "managerModel.account")
    @Mapping(target = "admin", source = "managerModel.admin")
    @Mapping(target = "store", source = "managerModel.store")
    @Mapping(target = "createdAt", source = "managerModel.createdAt")
    @Mapping(target = "updatedAt", source = "managerModel.updatedAt")
    ManagerResponseDTO managerModelToManagerResponseDTO(ManagerModel managerModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ManagerModel managerRequestDTOToManagerModel(ManagerRequestDTO managerRequestDTO);
}
