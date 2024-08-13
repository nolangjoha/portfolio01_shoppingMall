package com.smilemall.basic.payinfo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayInfoService {

	private final PayInfoMapper payInfoMapper;
	
	
	// [결제정보 입력]
	public void payInfo_insert(PayInfoVo vo) {
		payInfoMapper.payInfo_insert(vo);
	}
	
	// [결제정보 가져오기]
	public PayInfoVo ord_pay_info(Long ord_code) {
		return payInfoMapper.ord_pay_info(ord_code);
	}
	
}
