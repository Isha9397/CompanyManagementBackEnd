package com.example.demo.ResponseHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MyResponse
{
	public static ResponseEntity<Object> generateCustomResponseFormat(String message, HttpStatus status, Object responseObj)
	{
		
		Map<String, Object> mapObject = new HashMap<String,Object>();
		
		mapObject.put("Message", message);
		mapObject.put("Status", status.value());
		mapObject.put("Data", responseObj);
		return new ResponseEntity<Object>( mapObject, status);
	}

}
