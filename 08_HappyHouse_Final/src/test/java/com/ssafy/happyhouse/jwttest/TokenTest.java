package com.ssafy.happyhouse.jwttest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.happyhouse.util.JwtUtil;

@SpringBootTest
public class TokenTest {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenTest.class);
	@Autowired
	JwtUtil util;
	
	@Test
	public void tokenGenTest() {
		// given
		String id = "ssafyssafy";
		// when
		String token = util.createAuthToken(id);
		// then
		assertNotNull(token);
		logger.debug(token);
		
	}
}
