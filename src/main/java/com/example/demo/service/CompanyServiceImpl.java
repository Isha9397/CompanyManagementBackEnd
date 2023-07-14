package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.exceptions.CompanyCodeAlreadyExistsException;
import com.example.demo.exceptions.CompanyTurnOverIsLessException;
import com.example.demo.model.Company;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepo;
	
	@Override
	public List<Company> getAll(){

	    List<Company> companyList = companyRepo.findAll();
		if(companyList!=null && companyList.size()>0)
		{
			return companyList;
		}
		else
		{
			return null;
		}
		
	}
	
	@Override
	public Company getCompanyByCode(int code) {

		Optional <Company> reqCompany = companyRepo.findById(code);
		if(reqCompany.isPresent() )
		{
			return reqCompany.get();
		}
			return null;
		
	}
	
	@Override
	public Company addUpdateStockPrice(Company company) throws CompanyCodeAlreadyExistsException,CompanyTurnOverIsLessException{

		Optional<Company> companyObj=companyRepo.findById(company.getCompanyCode());
		if(companyObj.isPresent())
		{
			throw new CompanyCodeAlreadyExistsException();
		}
		if(company.getCompanyTurnOver()<10000000)
		{
			throw new CompanyTurnOverIsLessException();
		}
		companyRepo.saveAndFlush(company);
		return company;

	}
	
	@Override
	public boolean deleteCompany(int code) {
		companyRepo.deleteById(code);
		return true;
	}

	@Override
	public boolean updateCompany(Company company) 
	{
		Company company1 = companyRepo.getById(company.getCompanyCode());
		
		if(company1 !=null)
		{
			company1.setCompanyCEO(company.getCompanyCEO());
			company1.setCompanyName(company.getCompanyName());
			company1.setCompanyTurnOver(company.getCompanyTurnOver());
			company1.setCompanyWebsite(company.getCompanyWebsite());
			company1.setStockPrice(company.getStockPrice());
			companyRepo.saveAndFlush(company1);
			return true;
		}
		return false;
		
	}
}
