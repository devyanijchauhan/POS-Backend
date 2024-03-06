package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Admin.AdminRequestDTO;
import org.pgs.posback.DTO.Admin.AdminResponseDTO;
import org.pgs.posback.model.AdminModel;

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

//    @Mapping(target = "id", source = "adminModel.id")
//    @Mapping(target = "account", source = "adminModel.account")
//    @Mapping(target = "name", source = "adminModel.name")
//    @Mapping(target = "role", source = "adminModel.role")
//    @Mapping(target = "manager", source = "adminModel.manager")
//    @Mapping(target = "createdAt", source = "adminModel.createdAt")
//    @Mapping(target = "updatedAt", source = "adminModel.updatedAt")
 AdminResponseDTO adminModelToAdminResponseDTO(AdminModel adminModel);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "account", source = "adminRequestDTO.account")
//    @Mapping(target = "name", source = "adminRequestDTO.name")
//    @Mapping(target = "role", constant = "admin")
//    @Mapping(target = "manager", source = "adminRequestDTO.manager")
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
 AdminModel adminRequestDTOToAdminModel(AdminRequestDTO adminRequestDTO);
}
