package com.ssafy.happyhouse.util;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	// application.properties에 사용된 값 주입받기
	@Value("${jwt.salt")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	public String createAuthToken(String id) {
		return create(id, "authToken", expireMin);
	}
	
	// 로그인 성공 시 사용자 정보를 기반으로 JWTToken을 생성해서 반환함
	// JWT Token = Header + Payload + Signature
	private String create(String id, String subject, long expireMin) {
		
		final JwtBuilder builder = Jwts.builder();
		
		// Header 설정 - 토큰의 타입을 의미
		builder.setHeaderParam("typ", "JWT");
		
		// Payload 설정 - claim 정보 포함
		builder.setSubject(subject) // 토큰 제목 설정
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin)); // 유효기간 설정
		
		//담고 싶은 정보 설정
		if(id != null) {
			builder.claim("user", id);
		}
		
		// signature - secret key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		// 직렬화 처리
		final String jwt = builder.compact();
		logger.debug("토큰 발행: {}", jwt);
		return jwt;
	}
	
	// jwt 토큰을 분석해서 필요한 정보를 반환함
	// 토큰에 문제가 있다면 runtime exception 발생
	public Map<String, Object> checkAndGetClaims(String jwt){
		Jws<Claims> claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		// Claims: Map의 구현체
		logger.trace("claims: {}", claims);
		return claims.getBody();
	}
}
