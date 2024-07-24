package com.smilemall.basic.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {

	
	// 각 페이지에서 출력되는 시작, 끝 페이지
	private int startPage;
	private int endPage;
	
	// 다음, 이전 아이콘 표시여부 : 참이면 표시
	private boolean prev, next;
	
	// 테이블의 전체 데이터 갯수
	private int total;
	
	// Criteria getter메서드 사용
	private Criteria cri; 		//선택한 페이지, 페이지에 출력할 게시물 갯수

	
	
	//생성자 : 페이지 기능 구현
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		
		int pageSize = 10; // 블럭마다 보여질 페이지 번호 갯수
		int endPageInfo = pageSize - 1; 
		
		// 블럭별 끝 페이지(10)   //Math.ceil(cri.getPageNum()/ (double)pageSize)) : 뭘해도 1이 출력   // 1*pageSize = 1*10 = 10
		this.endPage = (int) (Math.ceil(cri.getPageNum()/ (double)pageSize)) * pageSize;
		
		// 10-9 = 1
		this.startPage = this.endPage - endPageInfo;
		
		// 실제 끝번호		   // (데이터 갯수 / 페이지마다 출력할 데이터의 양) 무조건 올림  // 1.0으로 계산하여 무조건 실수로 계산됨.
		int realEnd = (int) (Math.ceil((total*1.0)/cri.getAmount()));  // 예) total= 155,   155.0/10 = 15.5  => 16
		
		//실제 끝 번호가 10보다 작거나 같으면 끝번호는 realEnd로 출력
		if(realEnd <= this.endPage) {
			this.endPage = realEnd;
		}
		
		//이전, 다음 표시여부
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
	}
	
	
	
	
	
}
