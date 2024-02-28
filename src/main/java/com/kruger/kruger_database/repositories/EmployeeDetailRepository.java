package com.kruger.kruger_database.repositories;

import com.kruger.kruger_database.models.Employee;
import com.kruger.kruger_database.models.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {
}
