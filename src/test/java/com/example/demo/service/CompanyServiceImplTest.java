package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.Repository.CompanyRepository;
import com.example.demo.exceptions.CompanyTurnOverIsLessException;
import com.example.demo.model.Company;

public class CompanyServiceImplTest {
	
	@InjectMocks
	private CompanyServiceImpl userService;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private CompanyRepository userRepo;
	
	@BeforeEach
	public void init()
	{
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
	}

	List<Company> userList = new ArrayList<Company>();
	
	@Test
	public void getAllSuccess() throws Exception
	{
		Company user = new Company();
		user.setCompanyCode(101);
		user.setCompanyName("Keith");
		
		userList.add(user);
		when(userRepo.findAll()).thenReturn(userList);
		
		List<Company> uList = userService.getAll();
		assertEquals(userList, uList);
		
	}
	
	@Test
	public void getAllUsersFailure() throws Exception
	{
		
		when(userRepo.findAll()).thenReturn(null);
		
		List<Company> uList = userService.getAll();
		assertNull(uList);
		
	}
	
	@Test
	public void addUserSuccess() throws Exception
	{
		Company user = new Company();
		user.setCompanyCode(111);
		user.setCompanyName("Keith");
		user.setCompanyTurnOver(100000000.0);
		
		userList.add(user);
		when(userRepo.save(any())).thenReturn(user);
		
		Company u1 = userService.addUpdateStockPrice(user);
		assertEquals(user, u1);
		
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
