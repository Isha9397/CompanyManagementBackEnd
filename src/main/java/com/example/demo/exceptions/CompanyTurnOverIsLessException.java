package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="The Company turnOver is less than 10cr")
public class CompanyTurnOverIsLessException extends Exception{

}
