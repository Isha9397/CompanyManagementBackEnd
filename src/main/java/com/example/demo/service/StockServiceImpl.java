package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.StockRepository;
import com.example.demo.model.Stock;

@Service
public class StockServiceImpl implements StockService
{
	@Autowired
	private StockRepository rp;
	

	@Override
	public Set<Stock> getAllStocks(int code)
	{
		
		Set<Stock> stockList = rp.getStockList(code);
		return stockList;
	}

	@Override
	public boolean deleteStock(int code) {
		
	rp.deleteStockData(code);
	return true;
	}

	@Override
	public boolean addStock(Stock stock) 
	{
		Stock stockObj = new Stock();
		
		stockObj.setCompany_code_fk(stock.getCompany_code_fk());
		stockObj.setSetAt(stock.getSetAt());
		stockObj.setStockPrice(stock.getStockPrice());
		rp.saveAndFlush(stockObj);
		return true;
	
	}
	

}
















