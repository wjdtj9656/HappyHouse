package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Interest;
import com.ssafy.happyhouse.service.InterestService;

@CrossOrigin("*")
@RestController
@RequestMapping("/interest")
public class InterestController {
	
	private static final Logger logger = LoggerFactory.getLogger(InterestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	InterestService interestService;
	
	@PostMapping("{id}")
	public ResponseEntity<String> addInterest(Interest interest){
		logger.debug("관심매물 등록");
		if(interestService.addInterest(interest) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> interestList(@PathVariable String id){
		logger.debug("관심매물 조회");
		List<Interest> result = interestService.interestList(id);
		
		if(result != null) {
			return new ResponseEntity<List<Interest>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteInterest(String interestno){
		if(interestService.deleteInterest(interestno) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

}
