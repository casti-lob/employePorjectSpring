package com.jacaranda.employeeProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.employeeProject.model.Company;
import com.jacaranda.employeeProject.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	public List<Company> getCompanies(){
		return companyRepository.findAll();
	}
	
	public Company addCompany(Company c) {
		return companyRepository.save(c);
	}
	
	public Optional<Company> getCompany(Integer id) {
		return companyRepository.findById(id);
	}
	
	public Boolean updateCompany(Company c) {
		Boolean exist = false; 
		Company company = getCompany(c.getId()).orElse(null);
		if(company !=null) {
			exist = true;
			companyRepository.save(c);
		}
		return exist;
	}
	
	
}
