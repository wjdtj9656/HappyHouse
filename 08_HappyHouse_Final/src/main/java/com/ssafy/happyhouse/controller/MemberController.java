package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Member;
import com.ssafy.happyhouse.service.MemberService;
import com.ssafy.happyhouse.util.JwtUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtUtil jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member member, HttpServletResponse res, HttpServletRequest req) throws SQLException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Member loginUser = memberService.login(member.getId(), member.getPassword());
		// 생성된 토큰 정보를 클라이언트에게 전달한다
		resultMap.put("jwtAuthToken", loginUser.getAuthToken());
		return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerMember(@RequestBody Member member) throws SQLException{
		logger.debug("회원 등록: {}", member);
		int cnt = 0;
		cnt = memberService.registtMember(member);
		if (cnt>0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/idcheck")
	public ResponseEntity<Integer> idCheck(@RequestParam("ckid") String checkId) throws SQLException {
		logger.debug(checkId);
		return new ResponseEntity<Integer>(memberService.idCheck(checkId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> detailMember(@PathVariable String id) throws SQLException{
		logger.debug("회원 상세정보: {}", id);
		return new ResponseEntity<Member>(memberService.detailMember(id),HttpStatus.OK);
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<String> modifyMember(@PathVariable String id, @RequestBody Member member) throws SQLException{
		logger.debug("modifyMember 호출: {}", member);
		if(memberService.modifyMember(member) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteMember(@PathVariable String id) throws SQLException{
		logger.debug("deleteMember 호출");
		if(memberService.deleteMember(id) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	
	
	
}
