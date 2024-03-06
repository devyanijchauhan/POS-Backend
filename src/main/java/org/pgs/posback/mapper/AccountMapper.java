package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Account.AccountRequestDTO;
import org.pgs.posback.DTO.Account.AccountResponseDTO;
import org.pgs.posback.model.AccountModel;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "id", source = "accountModel.id")
    @Mapping(target = "username", source = "accountModel.username")
    @Mapping(target = "password", source = "accountModel.password")
    @Mapping(target = "role", source = "accountModel.role")
    @Mapping(target = "createdAt", source = "accountModel.createdAt")
    @Mapping(target = "updatedAt", source = "accountModel.updatedAt")
    AccountResponseDTO accountModelToAccountResponseDTO(AccountModel accountModel);

    @Mapping(target = "id", ignore = true)
    AccountModel accountRequestDTOToAccountModel(AccountRequestDTO accountRequestDTO);
}
