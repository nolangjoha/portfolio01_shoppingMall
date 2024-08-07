package com.smilemall.basic.kakaopay;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

//결제승인 요청 클래스

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApproveRequest {
    private String cid;				//가맹점코드
    private String tid;				//결제고유번호, 결제준비 api응답에 포함
    private String partnerOrderId;	//가맹점 주문번호, 결제준비 api요청과 일치해야 함.
    private String partnerUserId;	//가맹점 회원id, 결제준비 api요청과 일치해야 함.
    private String pgToken;			//결제승인 요청을 인증하는 토큰
}
