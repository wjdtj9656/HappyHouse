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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.service.CommentService;
import com.ssafy.happyhouse.service.NoticeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NoticeService noticeService;
	
	
	
	@GetMapping
	public ResponseEntity<List<Notice>> noticeList(){
		logger.debug("공지 목록 조회");
		return new ResponseEntity<List<Notice>>(noticeService.noticeList(), HttpStatus.OK);
	}
	
	@GetMapping("/{noticeno}")
	public ResponseEntity<Notice> detailNotice(@PathVariable String noticeno){
		logger.debug("공지 세부 조회");
		return new ResponseEntity<Notice>(noticeService.detailNotice(noticeno), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Notice>> searchNotice(@RequestParam String type, String keyword){
		if(type.equals("title")) {
			logger.debug("제목으로 공지 검색");
			return new ResponseEntity<List<Notice>>(noticeService.searchNoticeByTitle(keyword), HttpStatus.OK);
		} else if (type.equals("content")) {
			logger.debug("내용으로 공지 검색");
			return new ResponseEntity<List<Notice>>(noticeService.searchNoticeByContent(keyword), HttpStatus.OK);
		} else {
			logger.debug("검색 실패");
			return new ResponseEntity<List<Notice>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> insertNotice(@RequestBody Notice notice) {
		logger.debug("공지 등록: {}", notice);
		if (noticeService.insertNotice(notice) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/{noticeno}")
	public ResponseEntity<String> updateNotice(@RequestBody Notice notice){
		logger.debug("공지 수정");
		if (noticeService.updateNotice(notice) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{noticeno}")
	public ResponseEntity<String> deleteNotice(@PathVariable String noticeno) {
		logger.debug("공지 삭제");
		if (noticeService.deleteNotice(noticeno) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
}
