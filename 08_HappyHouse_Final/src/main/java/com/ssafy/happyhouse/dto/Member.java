package com.ssafy.happyhouse.dto;

public class Member {
	private String id;
	private int userclass;
	private String password;
	private String username;
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	private String email;
	private String authToken; // 사용자 인증 정보 토큰
	private String refreshToken; // authToken 갱신을 위한 토큰
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserclass() {
		return userclass;
	}
	public void setUserclass(int userclass) {
		this.userclass = userclass;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", userclass=" + userclass + ", password=" + password + ", username=" + username
				+ ", email=" + email + ", authToken=" + authToken + ", refreshToken=" + refreshToken + "]";
	}
	public Member() {
		
	}
	public Member(String id, int userclass, String password, String username, String email) {
		super();
		this.id = id;
		this.userclass = userclass;
		this.password = password;
		this.username = username;
		this.email = email;
	}
	
	
}
