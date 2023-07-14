package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.CompanyCodeAlreadyExistsException;
import com.example.demo.exceptions.CompanyTurnOverIsLessException;
import com.example.demo.model.Company;

public interface CompanyService {
	public List<Company> getAll();

	public Company getCompanyByCode(int code);
	
	public Company addUpdateStockPrice(Company Company) throws CompanyCodeAlreadyExistsException,CompanyTurnOverIsLessException;
	
	public boolean deleteCompany(int code);
	
	public boolean updateCompany(Company company);

}
