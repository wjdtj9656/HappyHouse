package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Member;

@Mapper
public interface MemberMapper {
	
	Member login(String id, String password) throws SQLException;
	public int idCheck(String checkId) throws SQLException;
	
	public int insertMember(Member member) throws SQLException;
	public Member selectMember(String id) throws SQLException;
	public int updateMember(Member member) throws SQLException;
	public int deleteMember(String id) throws SQLException;
	
}
