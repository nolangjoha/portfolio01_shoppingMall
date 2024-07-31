package com.smilemall.basic.review;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
 
	private final ReviewMapper reviewMapper;
	
	//[상품후기목록]
	public List<ReviewVo> rev_list(Integer pro_num) {
		return reviewMapper.rev_list(pro_num);
	}
	
}
