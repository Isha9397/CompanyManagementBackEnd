package com.example.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.demo.model.Userdto;

@RestController
@RequestMapping( value="api/consume",consumes="application/json")
public class ConsumerController 
{
	@PostMapping("/registerUser")
	public ResponseEntity<?> consumeRegisterUser(@RequestParam int id,@RequestParam String username,@RequestParam String password ) throws Exception
	{
	
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:8082/auth/user/registerUser";	
		System.out.println("--------------------"+baseUrl);
		ResponseEntity<String> response = null;	
		
		Userdto us=new Userdto();
		us.setId(id);
		us.setUsername(username);
		us.setPassword(password);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Userdto> request = new HttpEntity<>(us, headers);
		System.out.println("request is "+request);
		try
		{                                          
			//response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeader(), String.class);
			response = restTemplate.postForEntity(baseUrl, request,String.class);
			
			
			System.out.println("res"+response.getBody());
			System.out.println(response.getHeaders()+"-->"+ response.getStatusCodeValue());
			
				//return new ResponseEntity<User>(user, HttpStatus.CREATED);
				return new ResponseEntity<>(response, HttpStatus.OK);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return new ResponseEntity<String>("response object is null", HttpStatus.NO_CONTENT);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> consumeLogin(@RequestParam String username,@RequestParam String password ) throws Exception
	{
		RestTemplate restTemplate = new RestTemplate();	
		String baseUrl = "http://localhost:8082/auth/user/login";	
		ResponseEntity<String> response = null;	
		
		Userdto us=new Userdto();
		us.setUsername(username);
		us.setPassword(password);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Userdto> request = new HttpEntity<>(us, headers);
		System.out.println("login request is "+request);
		try
		{                                          
			//response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeader(), String.class);
			response = restTemplate.postForEntity(baseUrl, request,String.class);
			
			System.out.println(response.getBody());
			System.out.println(response.getHeaders()+"-->"+ response.getStatusCodeValue());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("response object is null", HttpStatus.NO_CONTENT);
		
	}
	
	private static HttpEntity<?> getHeader() throws Exception
	{
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
//		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}








