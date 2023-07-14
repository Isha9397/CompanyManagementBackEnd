package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.demo.model.Company;
import com.example.demo.model.Stock;
import com.example.demo.service.CompanyServiceImpl;
import com.example.demo.service.StockServiceImpl;

public class StockControllerTest {
	@InjectMocks
	private StockController userC;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private StockServiceImpl userService;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userC).build();
	}

	Set<Stock> cList = new HashSet<Stock>();
	
	@Test
	public void getAllSuccess() throws Exception
	{
		
		Set<Stock> stock1=new HashSet<>();
		Stock stock=new Stock();
		stock.setStockPrice(10.0);
		stock.setCompany_code_fk(1);
		stock1.add(stock);
		
		when(userService.getAllStocks(1)).thenReturn(cList);
		
		Set<Stock> uList = userService.getAllStocks(1);
		assertEquals(cList, uList);
		
mockMvc.perform(MockMvcRequestBuilders.get("/api/stock/getAllStocks/{code}").contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}
