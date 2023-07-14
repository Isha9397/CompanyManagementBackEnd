package com.example.demo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Stock 
{
	@Id
	@GeneratedValue
	private int transactionId;
	
	Double stockPrice;
	
    Date setAt;
	
	private int company_code_fk;
	
	

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Date getSetAt() {
		return setAt;
	}

	public void setSetAt(Date setAt) {
		this.setAt = setAt;
	}

	public int getCompany_code_fk() {
		return company_code_fk;
	}

	public void setCompany_code_fk(int company_code_fk) {
		this.company_code_fk = company_code_fk;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	
	
	@PrePersist
	public void setTimeStamp()
	{
		if(this.setAt == null)
		{
			SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			timeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Mumbai")); //IST
		}
	}


}














