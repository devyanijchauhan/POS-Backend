package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Employee.EmployeeRequestDTO;
import org.pgs.posback.DTO.Employee.EmployeeResponseDTO;
import org.pgs.posback.model.EmployeeModel;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(target = "id", source = "employeeModel.id")
    @Mapping(target = "name", source = "employeeModel.name")
    @Mapping(target = "role", source = "employeeModel.role")
    @Mapping(target = "contactNumber", source = "employeeModel.contactNumber")
    @Mapping(target = "hireDate", source = "employeeModel.hireDate")
    @Mapping(target = "salary", source = "employeeModel.salary")
    @Mapping(target = "createdAt", source = "employeeModel.createdAt")
    @Mapping(target = "updatedAt", source = "employeeModel.updatedAt")
    EmployeeResponseDTO employeeModelToEmployeeResponseDTO(EmployeeModel employeeModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    EmployeeModel employeeRequestDTOToEmployeeModel(EmployeeRequestDTO employeeRequestDTO);
}
