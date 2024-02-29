package org.pgs.posback.controller;

import org.pgs.posback.model.AdminModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    @GetMapping("/all")
    public List<AdminModel> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/{Id}")
    public AdminModel getAdminById(@PathVariable Long Id) {
        return adminRepository.findById(Id).orElse(null);
    }

    @PostMapping
    public AdminModel createAdmin(@RequestBody AdminModel admin) {
        admin.setCreatedAt(new Date());
        admin.setUpdatedAt(new Date());
        return adminRepository.save(admin);
    }

    @PutMapping("/{adminId}")
    public AdminModel updateAdmin(@PathVariable Long adminId, @RequestBody AdminModel adminDetails) {
        AdminModel admin = adminRepository.findById(adminId).orElse(null);
        if (admin != null) {
            // Update admin details
            admin.setName(adminDetails.getName());
            admin.setRole(adminDetails.getRole());

            // Assuming manager ID is stored in the manager object of AdminModel
            // If it's not, adjust accordingly based on your data model
            if (adminDetails.getManager() != null) {
                admin.setManager(adminDetails.getManager());
            }

            admin.setUpdatedAt(new Date());
            return adminRepository.save(admin);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{adminsId}")
    public void deleteAdmin(@PathVariable Long adminsId) {
        adminRepository.deleteById(adminsId);
    }
}
