package com.jacaranda.employeeProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	
}
