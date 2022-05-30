package com.ssafy.happyhouse.dto;

public class Notice {
	private int noticeno;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private String userid;
	public int getNoticeno() {
		return noticeno;
	}
	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public Notice() {
		
	}
	public Notice(int noticeno, String writer, String title, String content, String regdate, String userid) {
		super();
		this.noticeno = noticeno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.userid = userid;
	}
	
	
}
