package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Interest;

public interface InterestService {

	public int addInterest(Interest interest);
	public List<Interest> interestList(String id);
	public int deleteInterest(String interestno);
	
}
