package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Manager.ManagerRequestDTO;
import org.pgs.posback.DTO.Manager.ManagerResponseDTO;
import org.pgs.posback.mapper.ManagerMapper;
import org.pgs.posback.model.ManagerModel;
import org.pgs.posback.repository.ManagerRepository;
import org.pgs.posback.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public List<ManagerResponseDTO> getAllManagers() {
        List<ManagerModel> managers = managerRepository.findAll();
        return managers.stream()
                .map(ManagerMapper.INSTANCE::managerModelToManagerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerResponseDTO getManagerById(Long id) {
        Optional<ManagerModel> optionalManager = managerRepository.findById(id);
        return optionalManager.map(ManagerMapper.INSTANCE::managerModelToManagerResponseDTO).orElse(null);
    }

    @Override
    public ManagerResponseDTO createManager(ManagerRequestDTO managerRequestDTO) {
        ManagerModel manager = ManagerMapper.INSTANCE.managerRequestDTOToManagerModel(managerRequestDTO);
        manager.setCreatedAt(new Date());
        manager.setUpdatedAt(new Date());
        ManagerModel savedManager = managerRepository.save(manager);
        return ManagerMapper.INSTANCE.managerModelToManagerResponseDTO(savedManager);
    }

    @Override
    public ManagerResponseDTO updateManager(Long managerId, ManagerRequestDTO managerRequestDTO) {
        Optional<ManagerModel> optionalManager = managerRepository.findById(managerId);
        if (optionalManager.isPresent()) {
            ManagerModel manager = optionalManager.get();
            manager.setName(managerRequestDTO.getName());
            manager.setRole(managerRequestDTO.getRole());
            manager.setAccount(managerRequestDTO.getAccount());
            manager.setAdmin(managerRequestDTO.getAdmin());
            manager.setStore(managerRequestDTO.getStore());
            manager.setUpdatedAt(new Date());
            ManagerModel updatedManager = managerRepository.save(manager);
            return ManagerMapper.INSTANCE.managerModelToManagerResponseDTO(updatedManager);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteManager(Long managerId) {
        managerRepository.deleteById(managerId);
    }
}
