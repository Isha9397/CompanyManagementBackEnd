package com.example.demo.JwtFilter;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class SecurityFilter extends GenericFilterBean
{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		HttpServletResponse  httpRes = (HttpServletResponse) response;
		
		String authHeader = httpReq.getHeader("authorization");
		
		if(authHeader == null || !authHeader.startsWith("Bearer"))
		{
			throw new ServletException("Missing or invalid Authentication header");
		}
		
		String jwtToken = authHeader.substring(7);  // Bearer e748fhksot89ldnfdo947.du3j890nsk
		
		System.out.println(authHeader);
		
		Claims claims = Jwts.parser().setSigningKey("secret key").parseClaimsJws(jwtToken).getBody();
		
		httpReq.setAttribute("username", claims);
		
		chain.doFilter(request, response);
		
		
	}

}










