package com.jacaranda.employeeProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.employeeProject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
