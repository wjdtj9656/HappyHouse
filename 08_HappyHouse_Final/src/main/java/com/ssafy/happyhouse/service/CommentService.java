package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Comment;

public interface CommentService {

	public List<Comment> commentList(String noticeno);
	public int addComment(Comment comment);
	public int modifyComment(Comment comment);
	public int deleteComment(String commentno);
}
