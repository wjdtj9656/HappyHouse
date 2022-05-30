package com.ssafy.happyhouse.dto;

public class Interest {
	private int interestno;
	private String username;
	private String apartname;
	private String address;
	private String userid;
	private String date;
	private double x;
	private double y;
	private String price;
	public int getInterestno() {
		return interestno;
	}
	public void setInterestno(int interestno) {
		this.interestno = interestno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getApartname() {
		return apartname;
	}
	public void setApartname(String apartname) {
		this.apartname = apartname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Interest() {
		
	}
	public Interest(int interestno, String username, String apartname, String address, String userid,String date, double x, double y,String price) {
		super();
		this.interestno = interestno;
		this.username = username;
		this.apartname = apartname;
		this.address = address;
		this.userid = userid;
		this.date = date;
		this.x = x;
		this.y = y;
		this.price = price;
	}
	
	
}
