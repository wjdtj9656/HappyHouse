package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.Notice;

@Mapper
public interface NoticeMapper {
	
	public List<Notice> noticeList();
	public Notice selectNotice(String noticeno);
	public List<Notice> searchNoticeByTitle(String keyword);
	public List<Notice> searchNoticeByContent(String keyword);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(String noticeno);
	
	
}
