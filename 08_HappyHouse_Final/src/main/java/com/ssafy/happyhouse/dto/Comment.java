package com.ssafy.happyhouse.dto;

public class Comment {
	private int commentno;
	private int noticeno;
	private String writer;
	private String userid;
	private String content;
	private String regdate;
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public Comment() {
		
	}
	public Comment(int noticeno, String writer, String userid, String content, String regdate) {
		super();
		this.noticeno = noticeno;
		this.writer = writer;
		this.userid = userid;
		this.content = content;
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "Comment [commentno=" + commentno + ", noticeno=" + noticeno + ", writer=" + writer + ", userid="
				+ userid + ", content=" + content + ", regdate=" + regdate + "]";
	}
	
	

}
