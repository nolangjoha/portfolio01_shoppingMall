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
	
	
	
}
