package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Interest;

@Mapper
public interface InterestMapper {

	public int insertInterest(Interest interest);
	public List<Interest> interestList(String userid);
	public int deleteInterest(String interestno);
}
