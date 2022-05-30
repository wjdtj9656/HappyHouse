package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.Notice;
import com.ssafy.happyhouse.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<Notice> noticeList() {
		return noticeMapper.noticeList();
	}

	@Override
	public Notice detailNotice(String noticeno) {
		return noticeMapper.selectNotice(noticeno);
	}

	@Override
	public List<Notice> searchNoticeByTitle(String title) {
		return noticeMapper.searchNoticeByTitle(title);
	}

	@Override
	public List<Notice> searchNoticeByContent(String word) {
		return noticeMapper.searchNoticeByContent(word);
	}

	@Override
	public int insertNotice(Notice notice) {
		return noticeMapper.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public int deleteNotice(String noticeno) {
		return noticeMapper.deleteNotice(noticeno);
	}

}
