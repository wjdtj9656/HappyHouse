package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.dto.Member;

public interface MemberService {

	Member login(String id, String password) throws SQLException;
	public int idCheck(String id) throws SQLException;
	
	public int registtMember(Member member) throws SQLException;
	public Member detailMember(String id) throws SQLException;
	public int modifyMember(Member member) throws SQLException;
	public int deleteMember(String id) throws SQLException;
	
}
