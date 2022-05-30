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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.Comment;
import com.ssafy.happyhouse.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/{noticeno}")
	public ResponseEntity<?> commentsList(@PathVariable String noticeno){
		logger.debug("댓글 목록 불러오기: {}", commentService.commentList(noticeno).toArray().toString());
		return new ResponseEntity<List<Comment>>(commentService.commentList(noticeno), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addComment(@RequestBody Comment comment){
		logger.debug("댓글 추가: {}", comment);
		int cnt = commentService.addComment(comment);
		if (cnt > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	@PutMapping
	public ResponseEntity<String> modifyComment(@RequestBody Comment comment){
		logger.debug("댓글 수정: {}", comment);
		int cnt = 0;
		cnt = commentService.modifyComment(comment);
		if (cnt > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{commentno}")
	public ResponseEntity<String> deleteComment(@PathVariable String commentno){
		logger.debug("댓글 삭제: {}", commentno);
		int cnt = 0;
		cnt = commentService.deleteComment(commentno);
		if (cnt > 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
	}
	
}
