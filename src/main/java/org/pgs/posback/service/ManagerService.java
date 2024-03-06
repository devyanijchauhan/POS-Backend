package org.pgs.posback.service;

import org.pgs.posback.DTO.Manager.ManagerRequestDTO;
import org.pgs.posback.DTO.Manager.ManagerResponseDTO;

import java.util.List;

public interface ManagerService {

    List<ManagerResponseDTO> getAllManagers();

    ManagerResponseDTO getManagerById(Long id);

    ManagerResponseDTO createManager(ManagerRequestDTO managerRequestDTO);

    ManagerResponseDTO updateManager(Long managerId, ManagerRequestDTO managerRequestDTO);

    void deleteManager(Long managerId);
}
