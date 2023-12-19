package com.jacaranda.employeeProject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.employeeProject.model.Company;
import com.jacaranda.employeeProject.model.Employee;
import com.jacaranda.employeeProject.service.CompanyService;
import com.jacaranda.employeeProject.service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/listEmployee")
	public String listEmployee(Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber
			,@RequestParam("sizeNumber") Optional<Integer> sizeNumber
			) {
		Page<Employee> listEmployee = employeeService.getEmployees(pageNumber.orElse(1),sizeNumber.orElse(10));
		model.addAttribute("listEmployee", listEmployee);
		model.addAttribute("totalItems", listEmployee.getTotalElements());
		model.addAttribute("currentPage", pageNumber.orElse(1));
		model.addAttribute("totalPages", listEmployee.getTotalPages());

		return "employee/listEmployee";
	}
	
	@GetMapping("updateEmployee/{id}")
	public String updateEmployee(Model model, @PathVariable String id) {
		try {
			int idEmployee = Integer.parseInt(id);
			Employee updateEmployee = employeeService.getEmployee(idEmployee).orElse(null);
			if(updateEmployee==null) {
				model.addAttribute("msg", "No existe el empleado");
				model.addAttribute("class", "alert alert-danger");
				return "error";
			}
			List<Company> companies = companyService.getCompanies();
			model.addAttribute("updateEmployee", updateEmployee);
			model.addAttribute("listCompany", companies); 
			return "employee/updateEmployee";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("class", "alert alert-danger");
			return "error";
		}
		
	}
	
	@PostMapping("/updateEmployee/submit")
	public String updateEmployeeSubmit(Model model, @ModelAttribute("updateEmployee") Employee employee) {
		if(!employeeService.updateEmployee(employee)) {
			model.addAttribute("msg", "No existe el empleado");
			model.addAttribute("class", "alert alert-danger");
		}else {
			model.addAttribute("msg", "Actualizado con exito");
			model.addAttribute("class", "alert alert-success");
		}
		return "employee/updateEmployee";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployee(Model model) {
		Employee newEmployee = new Employee();
		model.addAttribute("newEmployee", newEmployee);
		model.addAttribute("listCompany", companyService.getCompanies());  
		return "employee/addEmployee";
	}
	
	@PostMapping("/addEmployee/submit")
	public String addEmployeeSubmit(Model model, @ModelAttribute("newEmployee") Employee employee) {
		/*Boolean ok = */employeeService.addEmployee(employee);
		/*if(!ok) {
			model.addAttribute("msg", "Ya existe una compañia");
			model.addAttribute("class", "alert alert-danger");
		}else {
			model.addAttribute("msg", "Creado con exito");
			model.addAttribute("class", "alert alert-success");
		}*/
		return "employee/addEmployee";
	}
	
}
