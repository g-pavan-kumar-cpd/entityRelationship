package com.example.entityrelation.service;

import com.example.entityrelation.dto.request.EmployeeRequest;
import com.example.entityrelation.dto.response.EmployeeResponse;
import com.example.entityrelation.mapper.EmployeeMapper;

import com.example.entityrelation.model.Department;
import com.example.entityrelation.model.Employee;
import com.example.entityrelation.repository.DepartmentRepository;
import com.example.entityrelation.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    private EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                         EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toEmployeeResponse).toList();
    }

    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElse(null);

        if (null != employee) {
            return employeeMapper.toEmployeeResponse(employee);
        }
        return null;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest != null) {
            Employee employee = employeeMapper.toEmployee(employeeRequest);
            Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + employeeRequest.getDepartmentId()));
            employee.setDepartment(department);
            Employee savedEmployee = employeeRepository.save(employee);
            return employeeMapper.toEmployeeResponse(savedEmployee);
        }
        return null;
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        if (id != null && employeeRequest != null) {
            Employee employee = employeeRepository.findById(id).orElse(null);
            
            if (employee != null) {
                employeeMapper.updateEmployeeFromDto(employeeRequest, employee);

                Employee updatedEmployee = employeeRepository.save(employee);
                return employeeMapper.toEmployeeResponse(updatedEmployee);
            }
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }




}
