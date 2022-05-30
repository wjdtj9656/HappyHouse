package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Comment;
import com.ssafy.happyhouse.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<Comment> commentList(String noticeno) {
		return commentMapper.selectAllComments(noticeno);
	}

	@Override
	public int addComment(Comment comment) {
		return commentMapper.insertComment(comment);
	}

	@Override
	public int modifyComment(Comment comment) {
		return commentMapper.updateComment(comment);
	}

	@Override
	public int deleteComment(String commentno) {
		return commentMapper.deleteComment(commentno);
	}

}
