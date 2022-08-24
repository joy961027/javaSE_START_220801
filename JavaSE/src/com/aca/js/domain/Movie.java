package com.aca.js.domain;
//로직을 작성하기 위함이 아닌, 오직 데이터만을 담기위한 자바 객체 = DTO(DATA TRANSFER OBJECT), VO(VALUE OBJECT)
public class Movie {
	private String url;
	private String title;
	
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
	
}
