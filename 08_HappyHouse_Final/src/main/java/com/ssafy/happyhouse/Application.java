package com.ssafy.happyhouse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.JwtInterceptor;
import com.ssafy.happyhouse.util.Crawling;

@SpringBootApplication
public class Application implements WebMvcConfigurer{


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("서버 실행");
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/map/**") // 기본 적용 경로
		.excludePathPatterns(Arrays.asList("/member/**")); // 적용 제외 경로
	}

}
