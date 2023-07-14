package com.example.demo.service;

import java.util.Set;

import com.example.demo.model.Stock;

public interface StockService 
{
	public Set<Stock> getAllStocks(int bookId);
	
	public boolean deleteStock(int bookId);
	
	public boolean addStock(Stock reader);
	

}
