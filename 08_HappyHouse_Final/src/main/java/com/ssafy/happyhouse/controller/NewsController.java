package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.service.NewsService;
import com.ssafy.happyhouse.util.Crawling;

@CrossOrigin("*")
@RestController
@RequestMapping("/news")
public class NewsController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private Crawling crawling;
	
	@GetMapping
	public ResponseEntity<List<News>> showNews() throws SQLException, IOException {
		logger.debug("뉴스 보기");
		int cnt = newsService.deleteAllNews();
		if (cnt > 0) {
			logger.debug("삭제 성공!");
		} else {
			logger.debug("삭제 실패!");
		}
		crawling.download("https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260");
		
		return new ResponseEntity<List<News>>(newsService.newsList(), HttpStatus.OK);
	}
	
	
}
