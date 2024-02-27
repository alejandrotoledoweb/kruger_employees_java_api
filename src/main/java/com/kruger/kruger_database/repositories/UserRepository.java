package com.kruger.kruger_database.repositories;

import com.kruger.kruger_database.models.Employee;
import com.kruger.kruger_database.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
