package com.smilemall.basic.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {

	private final ReviewService reviewService;
	

	@GetMapping("/revlist/{pro_num}")
	public ResponseEntity<List<ReviewVo>> revlist(@PathVariable("pro_num") int pro_num) throws Exception {
		
		ResponseEntity<List<ReviewVo>> entity = null;
		
		//후기목록
		List<ReviewVo> revlist = reviewService.rev_list(pro_num);
		

		entity = new ResponseEntity<List<ReviewVo>>(revlist, HttpStatus.OK);
		
		return entity; 
		
	}
	
	
	
}
