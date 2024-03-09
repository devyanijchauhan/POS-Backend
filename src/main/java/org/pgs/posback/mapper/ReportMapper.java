package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Report.ReportRequestDTO;
import org.pgs.posback.DTO.Report.ReportResponseDTO;
import org.pgs.posback.model.ReportModel;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    @Mapping(target = "id", source = "reportModel.id")
    @Mapping(target = "type", source = "reportModel.type")
    @Mapping(target = "date", source = "reportModel.date")
    @Mapping(target = "content", source = "reportModel.content")
    @Mapping(target = "createdAt", source = "reportModel.createdAt")
    @Mapping(target = "updatedAt", source = "reportModel.updatedAt")
    ReportResponseDTO reportModelToReportResponseDTO(ReportModel reportModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ReportModel reportRequestDTOToReportModel(ReportRequestDTO reportRequestDTO);
}
