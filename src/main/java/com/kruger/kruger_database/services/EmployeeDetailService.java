package com.kruger.kruger_database.services;

import com.kruger.kruger_database.dto.EmployeeCreationDTO;
import com.kruger.kruger_database.dto.EmployeeDetailDto;
import com.kruger.kruger_database.dto.GetEmployeeDetailDto;

public interface EmployeeDetailService {

    EmployeeDetailDto updateEmployeeDetail(EmployeeDetailDto employeeDetailDto);

    EmployeeDetailDto getEmployeeDetail(Long userId);

}

