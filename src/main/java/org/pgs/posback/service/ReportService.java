package org.pgs.posback.service;

import org.pgs.posback.DTO.Report.ReportRequestDTO;
import org.pgs.posback.DTO.Report.ReportResponseDTO;
import java.util.List;

public interface ReportService {
    List<ReportResponseDTO> getAllReports();

    ReportResponseDTO getReportById(Long id);

    ReportResponseDTO createReport(ReportRequestDTO reportRequestDTO);

    ReportResponseDTO updateReport(Long reportId, ReportRequestDTO reportRequestDTO);

    void deleteReport(Long reportId);
}
