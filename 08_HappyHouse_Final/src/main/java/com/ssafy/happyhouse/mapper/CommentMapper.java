package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> selectAllComments(String noticeno);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(String commentno);
}
