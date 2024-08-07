package com.smilemall.basic.kakaopay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KakaopayService {

	@Value("${kakaopay.api.secret.key}")
	private String kakaopaySecretKey; //
	
	@Value("${cid}")
	private String cid; //가맹점 코드
	
	@Value("${approval}")
	private String approval;   //결제 성공 시 redirect url
	
	@Value("${cancel}")
	private String cancel;    //결제 취소 시 redirect url

	@Value("${fail}")
	private String fail;      //결제 실패 시 redirect url
 
	
	
	private String tid; // 결제고유번호
	private String partnerOrderId; //가맹점 주문번호
	private String partnerUserId; //가맹점 회원id 
	
	
	// [결제 준비요청] ready // ReadyResponse: 결제응답 클래스
	public ReadyResponse ready(String partnerOrderId, String partnerUserId, String itemName, int quantity, int totalAmount, int taxFreeAmount, int vatAmount) {
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization", "SECRET_KEY " + kakaopaySecretKey); 
		headers.set("Content-Type", "application/json;charset=UTF-8");
		
		//결제요청 정보
		ReadyRequest readyRequest = ReadyRequest.builder()
				.cid(cid)
				.partnerOrderId(partnerOrderId)
				.partnerUserId(partnerUserId)
				.itemName(itemName)
				.quantity(quantity)
				.totalAmount(totalAmount)
				.taxFreeAmount(taxFreeAmount)
				.vatAmount(vatAmount)
				.approvalUrl(approval)
				.cancelUrl(cancel)
				.failUrl(fail)
				.build();
		
		log.info("결제요청정보:" + readyRequest);
		
		//요청
		HttpEntity<ReadyRequest> entityMap = new HttpEntity<>(readyRequest ,headers);
		
		log.info("카카오 결제요청 요청데이터"+entityMap);
		
		//응답
		ResponseEntity<ReadyResponse> response = new RestTemplate().postForEntity(
				"https://open-api.kakaopay.com/online/v1/payment/ready",
				entityMap,
				ReadyResponse.class
				);
		
		log.info("응답데이터"+ response);
		
		//결제응답
		ReadyResponse readyResponse = response.getBody();
		
		log.info("카카오 결제요청 응답데이터"+readyResponse);
		
		this.tid = readyResponse.getTid();
		this.partnerOrderId = partnerOrderId;
		this.partnerUserId = partnerUserId;
		
		
		return readyResponse; // 결제고유번호(tid), URL(next_redirect_pc_url)응답 받음.
	}
	
	
	//[결제 승인요청]
	public String approve(String pgToken) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "SECRET_KEY " + kakaopaySecretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//승인요청정보
		ApproveRequest approveRequest = ApproveRequest.builder()
					.cid(cid)
					.tid(tid)
					.partnerOrderId(partnerOrderId)
					.partnerUserId(partnerUserId)
					.pgToken(pgToken)
					.build();
		
		log.info("결제승인요청 정보"+ approveRequest);
		
		//승인 요청
		HttpEntity<ApproveRequest> entityMap = new HttpEntity<>(approveRequest, headers); 
		try {
			ResponseEntity<String> response = new RestTemplate().postForEntity(
					"https://open-api.kakaopay.com/online/v1/payment/approve", 
					entityMap,
					String.class
					);
			
			log.info("결제승인요청"+ response);
			
			String approveResponse = response.getBody();  //승인결과 저장
	
			log.info("결제승인요청 응답"+ approveResponse);
			
			return approveResponse;
		
		}catch(HttpStatusCodeException ex) {     // http응답을 처리할 때 발생할 수 있는 예외상황
			return ex.getResponseBodyAsString(); //http응답본문을 문자열로 반환
		}
	}
	
	
	
	
	
	
	
}
