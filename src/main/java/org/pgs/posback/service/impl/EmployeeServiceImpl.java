package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Employee.EmployeeRequestDTO;
import org.pgs.posback.DTO.Employee.EmployeeResponseDTO;
import org.pgs.posback.mapper.EmployeeMapper;
import org.pgs.posback.model.EmployeeModel;
import org.pgs.posback.repository.EmployeeRepository;
import org.pgs.posback.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<EmployeeModel> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper.INSTANCE::employeeModelToEmployeeResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Optional<EmployeeModel> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.map(EmployeeMapper.INSTANCE::employeeModelToEmployeeResponseDTO).orElse(null);
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        EmployeeModel employee = EmployeeMapper.INSTANCE.employeeRequestDTOToEmployeeModel(employeeRequestDTO);
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());
        EmployeeModel savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.employeeModelToEmployeeResponseDTO(savedEmployee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long employeeId, EmployeeRequestDTO employeeRequestDTO) {
        Optional<EmployeeModel> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            EmployeeModel employee = optionalEmployee.get();
            employee.setName(employeeRequestDTO.getName());
            employee.setRole(employeeRequestDTO.getRole());
            employee.setContactNumber(employeeRequestDTO.getContactNumber());
            employee.setHireDate(employeeRequestDTO.getHireDate());
            employee.setSalary(employeeRequestDTO.getSalary());
            employee.setUpdatedAt(new Date());
            EmployeeModel updatedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.INSTANCE.employeeModelToEmployeeResponseDTO(updatedEmployee);
        } else {
            return null; // or throw an exception
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
