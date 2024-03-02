package org.pgs.posback.controller;

import org.pgs.posback.model.EmployeeModel;
import org.pgs.posback.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('NICE')")
    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @GetMapping(path ="by-id/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long Id) {
        Optional<EmployeeModel> employeeData = employeeRepository.findById(Id);
        return employeeData.map(employeeModel -> new ResponseEntity<>(employeeModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employee) {
        try {
            employee.setCreatedAt(new Date());
            employee.setUpdatedAt(new Date());
            EmployeeModel createdEmployee = employeeRepository.save(employee);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeModel employeeModel) {
        Optional<EmployeeModel> employeeData = employeeRepository.findById(employeeId);

        if (employeeData.isPresent()) {
            EmployeeModel updatedEmployee = employeeData.get();
            updatedEmployee.setName(employeeModel.getName());
            updatedEmployee.setRole(employeeModel.getRole());
            updatedEmployee.setContactInformation(employeeModel.getContactInformation());
            updatedEmployee.setHireDate(employeeModel.getHireDate());
            updatedEmployee.setSalary(employeeModel.getSalary());
            updatedEmployee.setUpdatedAt(new Date());
            return new ResponseEntity<>(employeeRepository.save(updatedEmployee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{Ide}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long Ide) {
        try {
            employeeRepository.deleteById(Ide);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
