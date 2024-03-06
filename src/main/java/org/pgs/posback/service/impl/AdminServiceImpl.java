package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Admin.AdminRequestDTO;
import org.pgs.posback.DTO.Admin.AdminResponseDTO;
import org.pgs.posback.mapper.AdminMapper;
import org.pgs.posback.model.AdminModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        List<AdminModel> admins = adminRepository.findAll();
        return admins.stream()
                .map(AdminMapper.INSTANCE::adminModelToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        Optional<AdminModel> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.map(AdminMapper.INSTANCE::adminModelToAdminResponseDTO).orElse(null);
    }

    @Override
    public AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO) {
        AdminModel admin = AdminMapper.INSTANCE.adminRequestDTOToAdminModel(adminRequestDTO);
        admin.setRole("admin");
        admin.setCreatedAt(new Date());
        admin.setUpdatedAt(new Date());
        AdminModel savedAdmin = adminRepository.save(admin);
        return AdminMapper.INSTANCE.adminModelToAdminResponseDTO(savedAdmin);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long adminId, AdminRequestDTO adminRequestDTO) {
        Optional<AdminModel> optionalAdmin = adminRepository.findById(adminId);
        if (optionalAdmin.isPresent()) {
            AdminModel admin = optionalAdmin.get();
            admin.setName(adminRequestDTO.getName());
            admin.setManager(adminRequestDTO.getManagerId());
            admin.setUpdatedAt(new Date());
            AdminModel updatedAdmin = adminRepository.save(admin);
            return AdminMapper.INSTANCE.adminModelToAdminResponseDTO(updatedAdmin);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }
}
