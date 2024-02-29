package org.pgs.posback.controller;

import org.pgs.posback.model.ManagerModel;
import org.pgs.posback.repository.EmployeeRepository;
import org.pgs.posback.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    private ManagerRepository managerRepository;

    @Autowired
    public ManagerController(ManagerRepository managerRepository){
        this.managerRepository=managerRepository;
    }

    @GetMapping("/all")
    public List<ManagerModel> getAllManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ManagerModel> getManagerById(@PathVariable Long Id) {
        Optional<ManagerModel> managerData = managerRepository.findById(Id);
        return managerData.map(managerModel -> new ResponseEntity<>(managerModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ManagerModel> createManager(@RequestBody ManagerModel manager) {
        try {
            manager.setCreatedAt(new Date());
            manager.setUpdatedAt(new Date());
            ManagerModel createdManager = managerRepository.save(manager);
            return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{managerId}")
    public ResponseEntity<ManagerModel> updateManager(@PathVariable Long managerId, @RequestBody ManagerModel managerModel) {
        Optional<ManagerModel> managerData = managerRepository.findById(managerId);

        if (managerData.isPresent()) {
            ManagerModel updatedManager = managerData.get();
            updatedManager.setName(managerModel.getName());
            updatedManager.setRole(managerModel.getRole());
            updatedManager.setAccount(managerModel.getAccount());
            updatedManager.setAdmin(managerModel.getAdmin());
            updatedManager.setStore(managerModel.getStore());
            updatedManager.setUpdatedAt(new Date());
            return new ResponseEntity<>(managerRepository.save(updatedManager), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{Idm}")
    public ResponseEntity<HttpStatus> deleteManager(@PathVariable Long Idm) {
        try {
            managerRepository.deleteById(Idm);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
