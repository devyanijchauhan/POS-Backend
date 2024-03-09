package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.model.ReturnModel;
import org.pgs.posback.DTO.Return.ReturnRequestDTO;
import org.pgs.posback.DTO.Return.ReturnResponseDTO;

@Mapper
public interface ReturnMapper {

    ReturnMapper INSTANCE = Mappers.getMapper(ReturnMapper.class);

//    @Mapping(target = "id", source = "returnModel.id")
//    @Mapping(target = "date", source = "returnModel.date")
//    @Mapping(target = "reason", source = "returnModel.reason")
//    @Mapping(target = "refundedAmount", source = "returnModel.refundedAmount")
//    @Mapping(target = "sale", source = "returnModel.sale")
//    @Mapping(target = "invoice", source = "returnModel.invoice")
    ReturnResponseDTO returnModelToReturnResponseDTO(ReturnModel returnModel);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
    ReturnModel returnRequestDTOToReturnModel(ReturnRequestDTO returnRequestDTO);
}
