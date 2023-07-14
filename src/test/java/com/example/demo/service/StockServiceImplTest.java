package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.Repository.StockRepository;
import com.example.demo.exceptions.CompanyTurnOverIsLessException;
import com.example.demo.model.Company;
import com.example.demo.model.Stock;

public class StockServiceImplTest {
	
	@InjectMocks
	private StockServiceImpl userService;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private StockRepository userRepo;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
	}

	Set<Stock> userList = new HashSet<Stock>();
	
	@Test
	public void getAllSuccess() throws Exception
	{
		Set<Stock>s=new HashSet<>();
		Stock st=new Stock();
		st.setStockPrice(10.2);
		s.add(st);
		when(userRepo.getStockList(1)).thenReturn(s);
		
		Set<Stock> uList = userService.getAllStocks(10);
		assertEquals(userList, uList);
		
	}
	
	
	@Test
	public void addUserSuccess() throws Exception
	{
		Stock user = new Stock();
		user.setStockPrice(11.0);
		user.setStockPrice(100000000.0);
		
		userList.add(user);
		when(userRepo.save(any())).thenReturn(user);
		
		boolean b = userService.addStock(user);
		assertTrue( b);
		
	}
	
//	@Test(expected = )
//	public void addUserFailure() throws Exception
//	{
//		Company user = new Company();
//		user.setCompanyCode(111);
//		user.setCompanyName("Keith");
//		user.setCompanyTurnOver(10.0);
//		when(userRepo.save(any())).thenReturn(user);
//		
//		when(userService.addUpdateStockPrice(user)).thenThrow(CompanyTurnOverIsLessException.class);
//		
//		
//	}

}
