package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyServiceImpl;
import com.example.demo.service.StockServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CompanyControllerTest {

	@InjectMocks
	private CompanyController userC;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private CompanyServiceImpl userService;
	
	@Mock
	private StockServiceImpl stockService;
	
	@Mock
	private CompanyRepository companyRepo;
	
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userC).build();
	}

	List<Company> cList = new ArrayList<Company>();
	
	@Test
	public void getAllSuccess() throws Exception
	{
		Company company = new Company();
		company.setCompanyCode(101);
		company.setCompanyName("Keith");
		
		Set<Stock> stock1=new HashSet<>();
		Stock stock=new Stock();
		stock.setCompany_code_fk(101);
		stock1.add(stock);
		
		cList.add(company);
		when(userService.getAll()).thenReturn(cList);
		when(stockService.getAllStocks(101)).thenReturn(stock1);
		
		List<Company> uList = userService.getAll();
		assertEquals(cList, uList);
		
mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getAll").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void addUserSuccess() throws Exception
	{
		Company company = new Company();
		company.setCompanyCode(109);
		company.setCompanyName("Keith");
		company.setCompanyTurnOver(100000000.0);
		
		when(userService.addUpdateStockPrice(any())).thenReturn(company);
		cList.add(company);
		
		
		assertEquals(1,cList.size());
mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addCompany").contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@Test
	public void addUserFailure() throws Exception
	{
		
		when(userService.addUpdateStockPrice(any())).thenReturn(null);
		
		Company u1 = userService.addUpdateStockPrice(null);
		assertNull(u1);
		
mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/addUser").contentType(MediaType.APPLICATION_JSON)
.content(new ObjectMapper().writeValueAsString(u1))).andExpect(MockMvcResultMatchers.status().is4xxClientError());

		
	}
}
