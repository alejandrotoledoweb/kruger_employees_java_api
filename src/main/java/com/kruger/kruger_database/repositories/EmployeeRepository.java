package com.kruger.kruger_database.repositories;

import com.kruger.kruger_database.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCedula(String cedula);
    boolean existsByUserId(Long userId);
}
