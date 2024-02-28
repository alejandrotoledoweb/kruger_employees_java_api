package com.kruger.kruger_database.config;

import com.kruger.kruger_database.models.Employee;
import com.kruger.kruger_database.models.EmployeeDetail;
import com.kruger.kruger_database.models.Role;
import com.kruger.kruger_database.models.UserEntity;
import com.kruger.kruger_database.repositories.EmployeeDetailRepository;
import com.kruger.kruger_database.repositories.EmployeeRepository;
import com.kruger.kruger_database.repositories.RoleRepository;
import com.kruger.kruger_database.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    @Transactional
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder,
                                   EmployeeRepository employeeRepository,
                                   EmployeeDetailRepository employeeDetailRepository) {
        return args -> {
            // Check if the admin user already exists
            if (!userRepository.existsByUsername("admin")) {
                UserEntity user = new UserEntity();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("password"));
                userRepository.save(user);
                System.out.println("User admin created");
            }

            UserEntity adminUser = userRepository.findByUsername("admin");
            Role role = roleRepository.findByName("ADMIN").orElseGet(() -> {
                Role newRole = new Role();
                newRole.setName("ADMIN");
                return roleRepository.saveAndFlush(newRole);
            });

            Set<Role> roles = new HashSet<>();
            roles.add(role);
            adminUser.setRoles(roles);
            userRepository.save(adminUser);

            // Assuming the employee does not already exist for this user
            if (!employeeRepository.existsByUserId(adminUser.getId())) {
                Employee employee = new Employee();
                employee.setCedula("1234567890");
                employee.setNames("Admin");
                employee.setLastNames("User");
                employee.setEmail("admin@example.com");
                employee.setUser(adminUser);

                EmployeeDetail employeeDetail = new EmployeeDetail();
                // Set properties on employeeDetail as necessary
                // Since this example does not specify EmployeeDetail fields, add as required

                employee.setEmployeeDetail(employeeDetail); // Associate employeeDetail with employee
                employeeDetail.setEmployee(employee); // Back-reference for bidirectional relationship

                employeeRepository.save(employee); // Saving employee should also save employeeDetail due to CascadeType.ALL
                System.out.println("Admin employee and details created");
            }
        };
    }
}
