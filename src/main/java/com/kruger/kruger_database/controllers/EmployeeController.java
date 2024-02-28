package com.kruger.kruger_database.controllers;

import com.kruger.kruger_database.dto.EmployeeCreationDTO;
import com.kruger.kruger_database.repositories.UserRepository;
import com.kruger.kruger_database.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService,
                              PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.employeeService = employeeService;
    }

    @CrossOrigin("*")
    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeCreationDTO> createEmployee(@RequestBody EmployeeCreationDTO employeeCreationDTO) {

        return new ResponseEntity<>(employeeService.createEmployee(employeeCreationDTO),
                HttpStatus.CREATED);
    }



}
