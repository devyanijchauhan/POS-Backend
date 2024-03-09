package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Payment.PaymentRequestDTO;
import org.pgs.posback.DTO.Payment.PaymentResponseDTO;
import org.pgs.posback.model.PaymentModel;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentResponseDTO paymentModelToResponseDTO(PaymentModel paymentModel);

    PaymentModel paymentRequestDTOToModel(PaymentRequestDTO paymentRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updatePaymentFromRequestDTO(PaymentRequestDTO paymentRequestDTO, @MappingTarget PaymentModel paymentModel);
}