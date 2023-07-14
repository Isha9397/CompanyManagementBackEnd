package com.example.demo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="The Company Code is already present in Database")
public class CompanyCodeAlreadyExistsException extends Exception{

}
