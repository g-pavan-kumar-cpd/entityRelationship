package com.example.entityrelation.mapper;

import com.example.entityrelation.dto.request.EmployeeRequest;
import com.example.entityrelation.dto.response.EmployeeResponse;
import com.example.entityrelation.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    

    Employee toEmployee(EmployeeRequest employeeRequest);
    
    void updateEmployeeFromDto(EmployeeRequest employeeRequest, @MappingTarget Employee employee);
    
    EmployeeResponse toEmployeeResponse(Employee employee);
}
