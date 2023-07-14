package com.example.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseHandler.MyResponse;
import com.example.demo.exceptions.CompanyCodeAlreadyExistsException;
import com.example.demo.exceptions.CompanyTurnOverIsLessException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyServiceImpl;
import com.example.demo.service.StockServiceImpl;

@RestController
@RequestMapping("api/v1")
class CompanyController {
	
	@Autowired
	private CompanyServiceImpl companyService;
    @Autowired
	private StockServiceImpl stockService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll()
	{
		List<Company> companyList = companyService.getAll();
		if(companyList !=null)
		{
			
			//return MyResponse.generateCustomResponseFormat("Successfully retrieved data", HttpStatus.OK, companyList);
			//CacheControl cachec = CacheControl.maxAge(30, TimeUnit.MINUTES);
		    //return ResponseEntity.ok().cacheControl(cachec).body(MyResponse.generateCustomResponseFormat("success" , HttpStatus.OK,companyList));
			for(Company c : companyList)
			{
				Set<Stock> stockList=stockService.getAllStocks(c.getCompanyCode());
				System.out.println("stockList " +stockList);
				c.setStockList(stockList);
			}
			return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
		}
		
		//return new ResponseEntity<String>("companyList is empty", HttpStatus.NO_CONTENT);
		return MyResponse.generateCustomResponseFormat("Couldn't retrieved data", HttpStatus.CONFLICT, null);
		
	}
	
	@GetMapping("/getCompany/{code}")
	public ResponseEntity<?> getCompanyByCode(@PathVariable("code") int code)
	{
		Company reqcompany = companyService.getCompanyByCode(code);
		if(reqcompany !=null)
		{
			return new ResponseEntity<Company>(reqcompany, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("reqcompany is not available", HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/addCompany")
	public ResponseEntity<?> addUpdateStockPrice(@RequestBody Company company) throws CompanyCodeAlreadyExistsException,CompanyTurnOverIsLessException
	{
		if(companyService.addUpdateStockPrice(company)!=null)
		{
			return new ResponseEntity<Company>(company, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("company object is empty", HttpStatus.CONFLICT);
	}
	
	
	@DeleteMapping("/deleteCompany/{code}")
	public ResponseEntity<?> deleteCompany(@PathVariable("code") int code)
	{
		if(stockService.deleteStock(code) && companyService.deleteCompany(code))
		{
			return new ResponseEntity<String>("company record is deleted!", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>("company record cannot be deleted!", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company)
	{
		if(companyService.updateCompany(company))
		{
			return new ResponseEntity<>(company, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<String>("company record cannot be updated!", HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
