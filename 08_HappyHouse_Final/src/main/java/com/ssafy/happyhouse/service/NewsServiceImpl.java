package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	@Override
	public int addNews(List<News> arr) {
		int cnt = 0;
		for (News n : arr) {
			if (newsMapper.insertNews(n) > 0)
				cnt++;
		}
		return cnt;
	}

	@Override
	public List<News> newsList() {
		return newsMapper.selectAllNews();
	}

	@Override
	public int deleteAllNews() {
		return newsMapper.deleteAllNews();
	}

}
