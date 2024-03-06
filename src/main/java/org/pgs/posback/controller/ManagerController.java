package org.pgs.posback.controller;

import org.pgs.posback.DTO.Manager.ManagerRequestDTO;
import org.pgs.posback.DTO.Manager.ManagerResponseDTO;
import org.pgs.posback.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManagerResponseDTO>> getAllManagers() {
        List<ManagerResponseDTO> managers = managerService.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponseDTO> getManagerById(@PathVariable Long id) {
        ManagerResponseDTO manager = managerService.getManagerById(id);
        return manager != null ? ResponseEntity.ok(manager) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ManagerResponseDTO> createManager(@RequestBody ManagerRequestDTO managerRequestDTO) {
        ManagerResponseDTO createdManager = managerService.createManager(managerRequestDTO);
        return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
    }

    @PutMapping("/{managerId}")
    public ResponseEntity<ManagerResponseDTO> updateManager(@PathVariable Long managerId, @RequestBody ManagerRequestDTO managerRequestDTO) {
        ManagerResponseDTO updatedManager = managerService.updateManager(managerId, managerRequestDTO);
        return updatedManager != null ? ResponseEntity.ok(updatedManager) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long managerId) {
        managerService.deleteManager(managerId);
        return ResponseEntity.noContent().build();
    }
}
