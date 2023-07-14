package com.example.demo.Repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Stock;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Integer>
{
	@Query(value="select r from Stock r where r.company_code_fk= :companyCode")
	public Set<Stock> getStockList(int companyCode);
	
	@Modifying
	@Query(value="delete from Stock where company_code_fk= :companyCode")
	public void deleteStockData(int companyCode);
	

}
