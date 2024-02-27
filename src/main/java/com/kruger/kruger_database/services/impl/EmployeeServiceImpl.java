package com.kruger.kruger_database.services.impl;

import com.kruger.kruger_database.dto.EmployeeCreationDTO;
import com.kruger.kruger_database.exceptions.EmployeeAlreadyExistException;
import com.kruger.kruger_database.models.Employee;
import com.kruger.kruger_database.models.EmployeeDetail;
import com.kruger.kruger_database.models.Role;
import com.kruger.kruger_database.models.UserEntity;
import com.kruger.kruger_database.repositories.EmployeeDetailRepository;
import com.kruger.kruger_database.repositories.EmployeeRepository;
import com.kruger.kruger_database.repositories.RoleRepository;
import com.kruger.kruger_database.repositories.UserRepository;
import com.kruger.kruger_database.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeRepository employeeRepository;
    public EmployeeDetailRepository employeeDetailRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeDetailRepository employeeDetailRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeDetailRepository = employeeDetailRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

    }

    @Override
    public EmployeeCreationDTO createEmployee(EmployeeCreationDTO employeeCreationDTO) {

        if (employeeRepository.findByCedula(employeeCreationDTO.getCedula()) != null) {
            throw new EmployeeAlreadyExistException("An employee with cedula " + employeeCreationDTO.getCedula() + " already exists.");
        }

        String username = generateUsername(employeeCreationDTO.getNames(), employeeCreationDTO.getLastNames());
        String password = generateRandomPassword(10);

        Employee employee = mapToEntity(employeeCreationDTO);
        EmployeeDetail employeeDetail = new EmployeeDetail();
        employee.setEmployeeDetail(employeeDetail);
        employeeDetail.setEmployee(employee);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleRepository.findByName("EMPLOYEE").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

        employee.setUser(user);
        Employee newEmployee = employeeRepository.save(employee);

        EmployeeCreationDTO responseDto = mapToDto(newEmployee, password);
        responseDto.setUsername(username);

        return responseDto;
    }


    private EmployeeCreationDTO mapToDto(Employee employee, String password) {
        EmployeeCreationDTO employeeCreationDTO = new EmployeeCreationDTO();

        employeeCreationDTO.setId(employee.getId());
        employeeCreationDTO.setCedula(employee.getCedula());
        employeeCreationDTO.setNames(employee.getNames().toLowerCase());
        employeeCreationDTO.setLastNames(employee.getLastNames().toLowerCase());
        employeeCreationDTO.setEmail(employee.getEmail());
        employeeCreationDTO.setEmployeeDetail(employee.getEmployeeDetail());
        employeeCreationDTO.setUsername(employee.getUser().getUsername());
        employeeCreationDTO.setPassword(password);

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

    private String generateUsername(String names, String lastNames) {
        // Example: Concatenate first name and the first letter of last name
        String username = names.split(" ")[0] + lastNames.charAt(0);
        username = username.toLowerCase().replaceAll("\\s+", ""); // Remove spaces and make lowercase
        // Check if the username already exists and append a number if it does
        int num = 0;
        while (userRepository.existsByUsername(username + (num == 0 ? "" : num))) {
            num++;
        }
        return username + (num == 0 ? "" : num);
    }

    private String generateRandomPassword(int length) {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }
        return sb.toString();
    }

}
