package org.pgs.posback.service;

import org.pgs.posback.DTO.Employee.EmployeeRequestDTO;
import org.pgs.posback.DTO.Employee.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO updateEmployee(Long employeeId, EmployeeRequestDTO employeeRequestDTO);

    void deleteEmployee(Long employeeId);
}
