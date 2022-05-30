package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Member;
import com.ssafy.happyhouse.mapper.MemberMapper;
import com.ssafy.happyhouse.util.JwtUtil;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public Member login(String id, String password) throws SQLException {
		Member loginMember = memberMapper.login(id, password);
		
		if(loginMember.getId().equals(id)) {
			String authToken = jwtUtil.createAuthToken(id);
			loginMember.setAuthToken(authToken);
			return loginMember;
		} else {
			throw new RuntimeException("회원 정보가 존재하지 않습니다.");
		}
	}

	@Override
	public int idCheck(String checkId) throws SQLException {
		return memberMapper.idCheck(checkId);
	}

	@Override
	public int registtMember(Member member) throws SQLException {
		
		return memberMapper.insertMember(member);
	}

	@Override
	public Member detailMember(String id) throws SQLException {
		return memberMapper.selectMember(id);
	}

	@Override
	public int modifyMember(Member member) throws SQLException {
		return memberMapper.updateMember(member);
	}

	@Override
	public int deleteMember(String id) throws SQLException {
		return memberMapper.deleteMember(id);
	}

}
