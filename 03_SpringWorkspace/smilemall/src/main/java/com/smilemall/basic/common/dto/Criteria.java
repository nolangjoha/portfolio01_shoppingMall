package com.smilemall.basic.common.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	//페이지 필드
	private int pageNum;  // 선택한 페이지
	private int amount;	  // 페이지에 출력될 게시물 개수
	
	
	//생성자 : 기본페이지 1~10
	public Criteria() {
		this(1, 10);
	}
	
	//매개변수 생성자
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	

	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount);
				
		return builder.toUriString();
	}
	
	
}
