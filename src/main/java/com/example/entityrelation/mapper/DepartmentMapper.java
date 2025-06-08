package com.example.entityrelation.mapper;

import com.example.entityrelation.dto.request.DepartmentRequest;
import com.example.entityrelation.dto.response.DepartmentResponse;
import com.example.entityrelation.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {


    Department toDepartment(DepartmentRequest departmentRequest);

    void updateDepartmentFromDto(DepartmentRequest departmentRequest, @MappingTarget Department department);

    DepartmentResponse toDepartmentResponse(Department department);

}
