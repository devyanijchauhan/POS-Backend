package org.pgs.posback.controller;

import org.pgs.posback.DTO.Report.ReportRequestDTO;
import org.pgs.posback.DTO.Report.ReportResponseDTO;
import org.pgs.posback.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReportResponseDTO>> getAllReports() {
        List<ReportResponseDTO> reports = reportService.getAllReports();
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> getReportById(@PathVariable Long id) {
        ReportResponseDTO report = reportService.getReportById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ReportResponseDTO> createReport(@RequestBody ReportRequestDTO reportRequestDTO) {
        ReportResponseDTO createdReport = reportService.createReport(reportRequestDTO);
        return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<ReportResponseDTO> updateReport(@PathVariable Long reportId, @RequestBody ReportRequestDTO reportRequestDTO) {
        ReportResponseDTO updatedReport = reportService.updateReport(reportId, reportRequestDTO);
        return updatedReport != null ? ResponseEntity.ok(updatedReport) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{rId}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long rId) {
        reportService.deleteReport(rId);
        return ResponseEntity.noContent().build();
    }
}
