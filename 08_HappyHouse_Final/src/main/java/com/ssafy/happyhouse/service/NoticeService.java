package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.Notice;

public interface NoticeService {

	public List<Notice> noticeList();
	public Notice detailNotice(String noticeno);
	public List<Notice> searchNoticeByTitle(String keyword);
	public List<Notice> searchNoticeByContent(String keyword);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(String noticeno);
	
}
