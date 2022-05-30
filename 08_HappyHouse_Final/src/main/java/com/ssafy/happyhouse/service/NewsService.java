package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.News;

public interface NewsService {
	
	public int addNews(List<News> arr) throws SQLException;
	public List<News> newsList() throws SQLException;
	public int deleteAllNews() throws SQLException;
}
