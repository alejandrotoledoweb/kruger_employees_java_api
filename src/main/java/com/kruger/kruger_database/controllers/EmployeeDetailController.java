package com.kruger.kruger_database.controllers;

import com.kruger.kruger_database.dto.EmployeeCreationDTO;
import com.kruger.kruger_database.dto.EmployeeDetailDto;
import com.kruger.kruger_database.dto.GetEmployeeDetailDto;
import com.kruger.kruger_database.services.EmployeeDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmployeeDetailController {

    private EmployeeDetailService employeeDetailService;

    public EmployeeDetailController(EmployeeDetailService employeeDetailService) {
        this.employeeDetailService = employeeDetailService;
    }

    @CrossOrigin("*")
    @GetMapping ("/employee-detail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDetailDto> getEmployeeDetail(@RequestBody GetEmployeeDetailDto getEmployeeDetailDto) {

        return new ResponseEntity<>(employeeDetailService.getEmployeeDetail(getEmployeeDetailDto.getUserId()),
                HttpStatus.CREATED);
    }

    @Tag(name = "Employee details update", description = "end point employee to update " +
            "their information" +
            "user")
    @CrossOrigin("*")
    @PutMapping("/employee-detail/update")
    public ResponseEntity<EmployeeDetailDto> updateEmployeeDetail(@RequestBody EmployeeDetailDto employeeDetailDto) {
        EmployeeDetailDto updatedEmployeeDetailDto = employeeDetailService.updateEmployeeDetail(employeeDetailDto);
        return new ResponseEntity<>(updatedEmployeeDetailDto, HttpStatus.OK);
    }


}
