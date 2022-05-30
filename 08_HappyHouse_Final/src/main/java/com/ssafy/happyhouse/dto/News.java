package com.ssafy.happyhouse.dto;

public class News {
	private int newsno;
	private String url;
	private String title;
	private String writing;
	private String preview;
	private String imgsrc;
	
	
	public News(String url, String title, String writing, String preview, String imgsrc) {
		super();
		this.url = url;
		this.title = title;
		this.writing = writing;
		this.preview = preview;
		this.imgsrc = imgsrc;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public int getNewsno() {
		return newsno;
	}
	public void setNewsno(int newsno) {
		this.newsno = newsno;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriting() {
		return writing;
	}
	public void setWriting(String writing) {
		this.writing = writing;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public News() {
		
	}
	@Override
	public String toString() {
		return "News [newsno=" + newsno + ", url=" + url + ", title=" + title + ", writing=" + writing + ", preview="
				+ preview + ", imgsrc=" + imgsrc + "]";
	}
	
	
	
	
	
	
	
}
