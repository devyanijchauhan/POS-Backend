package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Report.ReportRequestDTO;
import org.pgs.posback.DTO.Report.ReportResponseDTO;
import org.pgs.posback.mapper.ReportMapper;
import org.pgs.posback.model.ReportModel;
import org.pgs.posback.repository.ReportRepository;
import org.pgs.posback.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<ReportResponseDTO> getAllReports() {
        List<ReportModel> reports = reportRepository.findAll();
        return reports.stream()
                .map(ReportMapper.INSTANCE::reportModelToReportResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReportResponseDTO getReportById(Long id) {
        Optional<ReportModel> optionalReport = reportRepository.findById(id);
        return optionalReport.map(ReportMapper.INSTANCE::reportModelToReportResponseDTO).orElse(null);
    }

    @Override
    public ReportResponseDTO createReport(ReportRequestDTO reportRequestDTO) {
        ReportModel reportModel = ReportMapper.INSTANCE.reportRequestDTOToReportModel(reportRequestDTO);
        reportModel.setCreatedAt(new Date());
        reportModel.setUpdatedAt(new Date());
        ReportModel savedReport = reportRepository.save(reportModel);
        return ReportMapper.INSTANCE.reportModelToReportResponseDTO(savedReport);
    }

    @Override
    public ReportResponseDTO updateReport(Long reportId, ReportRequestDTO reportRequestDTO) {
        Optional<ReportModel> optionalReport = reportRepository.findById(reportId);
        if (optionalReport.isPresent()) {
            ReportModel report = optionalReport.get();
            report.setType(reportRequestDTO.getType());
            report.setDate(reportRequestDTO.getDate());
            report.setContent(reportRequestDTO.getContent());
            report.setUpdatedAt(new Date());
            ReportModel updatedReport = reportRepository.save(report);
            return ReportMapper.INSTANCE.reportModelToReportResponseDTO(updatedReport);
        } else {
            return null; // Or throw an exception
        }
    }

    @Override
    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }
}
