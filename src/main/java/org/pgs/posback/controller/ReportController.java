package org.pgs.posback.controller;

import org.pgs.posback.model.ReportModel;
import org.pgs.posback.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportRepository reportRepository;

    @Autowired
    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/all")
    public List<ReportModel> getAllReports() {
        return reportRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportModel> getReportById(@PathVariable("id") Long id) {
        Optional<ReportModel> reportData = reportRepository.findById(id);
        return reportData.map(reportModel -> new ResponseEntity<>(reportModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReportModel> createReport(@RequestBody ReportModel reportModel) {
        try {
            reportModel.setCreatedAt(new Date());
            reportModel.setUpdatedAt(new Date());
            ReportModel createdReport = reportRepository.save(reportModel);
            return new ResponseEntity<>(createdReport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reportid}")
    public ResponseEntity<ReportModel> updateReport(@PathVariable("reportid") Long reportid, @RequestBody ReportModel reportModel) {
        Optional<ReportModel> reportData = reportRepository.findById(reportid);

        if (reportData.isPresent()) {
            ReportModel updatedReport = reportData.get();
            updatedReport.setType(reportModel.getType());
            updatedReport.setDate(reportModel.getDate());
            updatedReport.setContent(reportModel.getContent());
            updatedReport.setUpdatedAt(new Date());
            return new ResponseEntity<>(reportRepository.save(updatedReport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idr}")
    public ResponseEntity<HttpStatus> deleteReport(@PathVariable("idr") Long idr) {
        try {
            reportRepository.deleteById(idr);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
