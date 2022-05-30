package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<String> makeCooke(ExpiredJwtException e) {
		logger.debug("jwt token expired", e);
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}
}
