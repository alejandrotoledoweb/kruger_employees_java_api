package com.kruger.kruger_database.services.impl;

import com.kruger.kruger_database.dto.EmployeeCreationDTO;
import com.kruger.kruger_database.exceptions.EmployeeAlreadyExistException;
import com.kruger.kruger_database.models.Employee;
import com.kruger.kruger_database.models.EmployeeDetail;
import com.kruger.kruger_database.repositories.EmployeeDetailRepository;
import com.kruger.kruger_database.repositories.EmployeeRepository;
import com.kruger.kruger_database.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeRepository employeeRepository;
    public EmployeeDetailRepository employeeDetailRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDetailRepository employeeDetailRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDetailRepository = employeeDetailRepository;
    }

    @Override
    public EmployeeCreationDTO createEmployee(EmployeeCreationDTO employeeCreationDTO) {

        if (employeeRepository.findByCedula(employeeCreationDTO.getCedula()) != null) {
            throw new EmployeeAlreadyExistException("An employee with cedula " + employeeCreationDTO.getCedula() + " already exists.");
        }

        Employee employee = mapToEntity(employeeCreationDTO);
        EmployeeDetail employeeDetail = new EmployeeDetail();
        employee.setEmployeeDetail(employeeDetail);
        employeeDetail.setEmployee(employee);
        Employee newEmployee = employeeRepository.save(employee);
        return mapToDto(newEmployee);
    }


    private EmployeeCreationDTO mapToDto(Employee employee) {
        EmployeeCreationDTO employeeCreationDTO = new EmployeeCreationDTO();

        employeeCreationDTO.setId(employee.getId());
        employeeCreationDTO.setCedula(employee.getCedula());
        employeeCreationDTO.setNames(employee.getNames().toLowerCase());
        employeeCreationDTO.setLastNames(employee.getLastNames().toLowerCase());
        employeeCreationDTO.setEmail(employee.getEmail());
        employeeCreationDTO.setEmployeeDetail(employee.getEmployeeDetail());

        return employeeCreationDTO;
    }

    private Employee mapToEntity(EmployeeCreationDTO employeeCreationDTO) {
        Employee employee = new Employee();

        employee.setId(employeeCreationDTO.getId());
        employee.setCedula(employeeCreationDTO.getCedula());
        employee.setNames(employeeCreationDTO.getNames().toLowerCase());
        employee.setLastNames(employeeCreationDTO.getLastNames().toLowerCase());
        employee.setEmail(employeeCreationDTO.getEmail());
        employee.setEmployeeDetail(employeeCreationDTO.getEmployeeDetail());

        return employee;
    }
}
