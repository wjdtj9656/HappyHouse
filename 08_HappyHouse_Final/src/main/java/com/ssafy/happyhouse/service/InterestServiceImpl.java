package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Interest;
import com.ssafy.happyhouse.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestMapper interestMapper;

	@Override
	public int addInterest(Interest interest) {
		return interestMapper.insertInterest(interest);
	}

	@Override
	public List<Interest> interestList(String id) {
		return interestMapper.interestList(id);
	}

	@Override
	public int deleteInterest(String interestno) {
		return interestMapper.deleteInterest(interestno);
	}

}
