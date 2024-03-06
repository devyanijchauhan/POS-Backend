package org.pgs.posback.controller;

import org.pgs.posback.DTO.Admin.AdminRequestDTO;
import org.pgs.posback.DTO.Admin.AdminResponseDTO;
import org.pgs.posback.model.AdminModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.repository.EmployeeRepository;
import org.pgs.posback.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins() {
        List<AdminResponseDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long Id) {
        AdminResponseDTO admin = adminService.getAdminById(Id);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AdminResponseDTO> createAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        AdminResponseDTO createdAdmin = adminService.createAdmin(adminRequestDTO);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{adminIds}")
    public ResponseEntity<AdminResponseDTO> updateAdmin(@PathVariable Long adminIds, @RequestBody AdminRequestDTO adminRequestDTO) {
        AdminResponseDTO updatedAdmin = adminService.updateAdmin(adminIds, adminRequestDTO);
        return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{adminsId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminsId) {
        adminService.deleteAdmin(adminsId);
        return ResponseEntity.noContent().build();
    }
}
