package com.jacaranda.employeeProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jacaranda.employeeProject.model.Company;
import com.jacaranda.employeeProject.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/listCompany")
	public String listCompanies(Model model) {
		List<Company> listCompanies = companyService.getCompanies();
		model.addAttribute("listCompanies", listCompanies);
		return "listCompanies";
	}
	
	@GetMapping("/addCompany")
	public String addCompany(Model model) {
		Company newCompany = new Company();
		model.addAttribute("newCompany", newCompany);
		return "addCompany";
				
	}
	
	@PostMapping("/addCompany/submit")
	public String addCompanySubmit(Model model, @ModelAttribute("newCompany") Company company) {
		companyService.addCompany(company);
		model.addAttribute("msg", "Creado con exito");
		return "addCompany";
	}
	
	@GetMapping("/updateCompany/{id}")
	public String updateCompany(Model model, @PathVariable Integer id) {
		Company updateCompany = companyService.getCompany(id).orElse(null);
		
		model.addAttribute("updateCompany", updateCompany);
		return "updateCompany";
				
	}
	
	@PostMapping("/updateCompany/submit")
	public String updateCompanySubmit(Model model, @ModelAttribute("updateCompany") Company company) {
		
		if(!companyService.updateCompany(company)) {
			model.addAttribute("msg", "No existe");
			model.addAttribute("class", "alert alert-danger");
			
		}else {
			
			model.addAttribute("msg", "Actualizado con exito");
			model.addAttribute("class", "alert alert-success");
		}
		
		return "updateCompany";
	}
}
