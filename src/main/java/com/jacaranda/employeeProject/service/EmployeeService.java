package com.jacaranda.employeeProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeProject.model.Employee;
import com.jacaranda.employeeProject.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Page<Employee> getEmployees(int pageNum, int pageSize){
		Pageable pageable =  PageRequest.of(pageNum -1, pageSize);
		return employeeRepository.findAll(pageable);
	}
	
	public void addEmployee(Employee e) {
		Boolean isNewEmployee = false;
		/*Employee employee = getEmployee(e.getId()).orElse(null);
		if(employee==null) {
			isNewEmployee = true;
			employeeRepository.save(e);
		}*/
		employeeRepository.save(e);
		//return isNewEmployee;
	}
	
	public Optional<Employee> getEmployee(Integer id){
		return employeeRepository.findById(id);
	}
	
	public Boolean updateEmployee(Employee e) {
		Boolean exist = false;
		Employee employee = getEmployee(e.getId()).orElse(null);
		if(employee!=null) {
			exist = true;
			employeeRepository.save(e);
		}
		return exist;
	}
	
}
