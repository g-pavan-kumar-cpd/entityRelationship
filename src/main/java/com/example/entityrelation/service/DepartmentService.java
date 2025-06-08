package com.example.entityrelation.service;


import com.example.entityrelation.dto.request.DepartmentRequest;
import com.example.entityrelation.dto.response.DepartmentResponse;
import com.example.entityrelation.mapper.DepartmentMapper;
import com.example.entityrelation.model.Department;
import com.example.entityrelation.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository,
                             DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(departmentMapper::toDepartmentResponse).toList();
    }


    public DepartmentResponse getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElse(null);

        if (null != department) {
            return departmentMapper.toDepartmentResponse(department);
        }
        return null;
    }


    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        if (departmentRequest != null) {
            Department department = departmentMapper.toDepartment(departmentRequest);
            Department savedDepartment = departmentRepository.save(department);
            return departmentMapper.toDepartmentResponse(savedDepartment);
        }
        return null;
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentRequest departmentRequest) {
        if (id != null && departmentRequest != null) {
            Department department = departmentRepository.findById(id).orElse(null);

            departmentMapper.updateDepartmentFromDto(departmentRequest, department);


        }
        return null;
    }



    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

}
