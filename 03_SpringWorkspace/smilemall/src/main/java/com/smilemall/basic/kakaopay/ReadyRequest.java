package com.smilemall.basic.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//결제요청 클래스

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReadyRequest {
    private String cid;					//가맹점 코드
    private String partnerOrderId;		//가맹점 주문번호
    private String partnerUserId;		//가맹점 회원id
    private String itemName;			//상품명
    private Integer quantity;			//상품수량
    private Integer totalAmount;		//상품총액
    private Integer taxFreeAmount;		//상품비과세 금액
    private Integer vatAmount;			//상품부가세 금액
    private String approvalUrl;			//결제성공시 redirect url
    private String cancelUrl;			//결제취소시 redirect url
    private String failUrl;				//결제실패시 redirect url
}
