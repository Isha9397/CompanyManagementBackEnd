package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.demo.JwtFilter.SecurityFilter;



@SpringBootApplication
public class CompanyManagementApplication {

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean obj = new FilterRegistrationBean();
		obj.setFilter(new SecurityFilter());
		obj.addUrlPatterns("/api/v1/*");
		return obj;
	}
	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementApplication.class, args);
	}

}
