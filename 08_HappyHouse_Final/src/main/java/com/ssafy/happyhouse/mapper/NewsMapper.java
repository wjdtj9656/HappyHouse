package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.News;

@Mapper
public interface NewsMapper {
	
	public int insertNews(News news);
	public List<News> selectAllNews();
	public int deleteAllNews();
	
}
