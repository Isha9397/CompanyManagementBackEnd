package com.example.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyService;
import com.example.demo.service.StockService;

@RestController
@RequestMapping("api/stock")
public class StockController 
{
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private CompanyService bs2;
	
	@PostMapping("/add/{code}")
	public ResponseEntity<?> addStock(@PathVariable("code") int code, @RequestBody Stock stock)
	{
		Company existCompany = bs2.getCompanyByCode(code);
		if(existCompany !=null)
		{
			existCompany.setStockPrice(stock.getStockPrice());//this will go to book table.. this will be DML operation
			//existCompany.setStockPriceList(stock.getStockPrice());
			stock.setCompany_code_fk(stock.getCompany_code_fk());///this will go to Reader table
			stock.setSetAt(stock.getSetAt());//this will go to Reader table
			
			if(bs2.updateCompany(existCompany) && stockService.addStock(stock))
					
				return new ResponseEntity<Stock>(stock, HttpStatus.CREATED);
					}
			return new ResponseEntity<>("Stock name cannot be added", HttpStatus.INTERNAL_SERVER_ERROR);
			
		
		
	}
	
	@GetMapping("/getAllStocks")
	public ResponseEntity<?> getAllStocks(@RequestParam("code") int code)  
	{

	     Set<Stock> stockList = stockService.getAllStocks(code);
	      if(stockList != null && !stockList.isEmpty())
	      {        
	    CacheControl cache = CacheControl.maxAge(30, TimeUnit.MINUTES);
	     return new ResponseEntity<Object>(stockList, HttpStatus.OK);
	        }
	       return new ResponseEntity<String>("Stocks not available", HttpStatus.NO_CONTENT);

	}
}














