package com.ssafy.happyhouse.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.dto.News;
import com.ssafy.happyhouse.service.NewsService;

@Component
public class Crawling {
	
	public ArrayList<News> arr = new ArrayList<>();
	
	private static final Logger logger = LoggerFactory.getLogger(Crawling.class);
	
	@Autowired
	private NewsService newsService;
	
	public void download(String address) throws SQLException, IOException {

		
		//만약 크롤링해서 인터넷뉴스에 있는 제목을 따올려고 할때, F12버튼을 눌러 소스를 일일히 확인해서 뭘 split으로 잘라서 가져와야할지 확인해야하고,
	    //만약 여러개를 찾으려고 하면은 다 달라서 힘들지만, jsoup는 그런 것들을 단순화 시켜서 찾기 쉽게끔 문서화처럼 시켜준다고 보면 된다. 그래서 찾을때도
	    //뉴스제목이 articleTitle인데 찾으려는 값이 뭔지만 안다면 그것만 입력해주면 알아서 찾아주기에 훨씬 편리하다.
		
		arr.clear();
		Document doc = Jsoup.connect(address)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36")
	            .get();
		
		logger.debug("크롤링 시작");
		//내부적으로 Elements는 Element들을 가지고있다.
		Elements elements =  doc.select(".type06_headline");
		for (Element el : elements.select("li")) {
			String url, title, writing, preview, imgsrc;
			imgsrc = el.select(".photo").select("img").attr("src");
			if (imgsrc == null) {
				title = el.select("dt").text();
			} else {
				title = el.select("dt").next().text();
			}
			preview = el.select(".lede").text();
			writing = el.select(".writing").text();
			Elements link = el.select("dt").select("a");
			url = link.attr("href");
			
			News n = new News(url, title, writing, preview, imgsrc);
			logger.debug(n.toString());
			arr.add(n);
		}
		
		logger.debug("newsService 호출");
		logger.debug(arr.toString());
		newsService.addNews(arr);
		


	}
	

}
