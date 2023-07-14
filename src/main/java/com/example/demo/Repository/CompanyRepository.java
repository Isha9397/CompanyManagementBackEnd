package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Company;
import com.example.demo.model.Stock;

import java.util.Set;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Integer>{
	
}
