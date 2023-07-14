package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company {
	@Id
	private int companyCode;
	public String companyName;
	public String companyCEO;
	public Double companyTurnOver;
	public Double stockPrice;
	public String companyWebsite;
	
	@JsonIgnore
	@OneToMany(targetEntity=Stock.class)
	private Set<Stock> stockList;
	
	
	public Set<Stock> getStockList() {
		return stockList;
	}
	public void setStockList(Set<Stock> stockList) {
		this.stockList = stockList;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	@Override
	public String toString() {
		return "Company [companyCode=" + companyCode + ", companyName=" + companyName + ", companyCEO=" + companyCEO
				+ ", companyTurnOver=" + companyTurnOver + ", stockPrice=" + stockPrice + ", companyWebsite="
				+ companyWebsite + ", stockList=" + stockList + "]";
	}
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(int companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	public Double getCompanyTurnOver() {
		return companyTurnOver;
	}
	public void setCompanyTurnOver(Double companyTurnOver) {
		this.companyTurnOver = companyTurnOver;
	}
	public Company(int companyCode, String companyName, String companyCEO, Double companyTurnOver,String companyWebsite) {
		super();
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyCEO = companyCEO;
		this.companyTurnOver = companyTurnOver;
		this.companyWebsite= companyWebsite;
	}

}
