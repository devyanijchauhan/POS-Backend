package org.pgs.posback.service;

import org.pgs.posback.DTO.Admin.AdminRequestDTO;
import org.pgs.posback.DTO.Admin.AdminResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {

    List<AdminResponseDTO> getAllAdmins();

    AdminResponseDTO getAdminById(Long id);

    AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO);

    AdminResponseDTO updateAdmin(Long adminId, AdminRequestDTO adminRequestDTO);

    void deleteAdmin(Long adminId);
}
