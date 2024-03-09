package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Transaction.TransactionRequestDTO;
import org.pgs.posback.DTO.Transaction.TransactionResponseDTO;
import org.pgs.posback.model.TransactionModel;

@Mapper
public interface  TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "id", source = "transactionModel.id")
    @Mapping(target = "type", source = "transactionModel.type")
    @Mapping(target = "amount", source = "transactionModel.amount")
    @Mapping(target = "dateTime", source = "transactionModel.dateTime")
    @Mapping(target = "store", source = "transactionModel.store")
    TransactionResponseDTO transactionModelToTransactionResponseDTO(TransactionModel transactionModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TransactionModel transactionRequestDTOToTransactionModel(TransactionRequestDTO transactionRequestDTO);
}
