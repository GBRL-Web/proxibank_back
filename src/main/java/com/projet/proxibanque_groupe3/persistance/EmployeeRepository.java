package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Set<Employee>> getEmployeesByRole(String role);
    Optional<Set<Employee>> findByClientsNotNull();
}
