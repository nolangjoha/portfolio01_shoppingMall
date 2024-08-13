package com.smilemall.basic.payinfo;

public interface PayInfoMapper {

	// [결제정보 입력]
	void payInfo_insert(PayInfoVo vo);
	
	// [결제정보 가져오기]
	PayInfoVo ord_pay_info (Long ord_code);
	
}
