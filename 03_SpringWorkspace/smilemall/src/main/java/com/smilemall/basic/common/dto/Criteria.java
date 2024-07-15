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
	
	//검색필드
	private String type;
	private String keyword;
	
	
	
	//생성자 : 기본페이지 1~10
	public Criteria() {
		this(1, 10);
	}
	
	//매개변수 생성자
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// xml파일에서 검색어 값을 넘겨주기 위힌 메서드
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
		//type이 null값인 경우 공백상태, 값이 있다면 split을 사용하여 검색조건 유형별 전달.
	}
	
	

	//페이징, 검색시 url 생성
	public String getListLink() {
		
		//UriComponentsBuilder : 여러개의 파라미터를 연결하여 URL 형태로 만들어 주는 기능
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
				
		return builder.toUriString();
	}
	
	
}
