package com.smilemall.basic.admin.carousel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smilemall.basic.common.dto.Criteria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCarouselService {

	private final AdminCarouselMapper adminCarouselMapper;
	
	//[캐러셀 등록]
	public void carousel_insert(CarouselVo vo) {
		adminCarouselMapper.carousel_insert(vo);
	}
	
	// [캐러셀 목록]
	public List<CarouselVo> carousel_list (Criteria cri, String carousel_view_status, String start_date, String end_date) {
		return adminCarouselMapper.carousel_list(cri, carousel_view_status, start_date, end_date);
	}
	
	// [전체 데이터 개수]
	public int getTotalCount(Criteria cri, String carousel_view_status, String start_date, String end_date) {
		return adminCarouselMapper.getTotalCount(cri, carousel_view_status, start_date, end_date);
	}
	
	// [메인페이지 캐러셀 출력]
	public List<CarouselVo> carousel_post_list () {
		return adminCarouselMapper.carousel_post_list();
	}
	
	// [캐러셀 수정 데이터 불러오기]
	public CarouselVo carousel_edit_data(Integer carousel_num) {
		return adminCarouselMapper.carousel_edit_data(carousel_num);
	}
	
	// [캐러셀 수정]
	public void carousel_edit (CarouselVo vo) {
		adminCarouselMapper.carousel_edit(vo);
	}
	
	// [캐러셀 삭제]
	public void carousel_delete(Integer carousel_num) {
		adminCarouselMapper.carousel_delete(carousel_num);
	}
	
	// [게시상태 일괄수정]
	public void carousel_checked_edit (List<Integer> carousel_num_arr, List<String> carousel_view_status_arr) {
		
		List<CarouselDTO> carousel_checked_list = new ArrayList<>();
		
		for(int i=0; i<carousel_num_arr.size(); i++) {
			CarouselDTO carouselDTO = new CarouselDTO(carousel_num_arr.get(i), carousel_view_status_arr.get(i));
			
			carousel_checked_list.add(carouselDTO);
		}
		
		// 체크된 목록 수정
		adminCarouselMapper.carousel_checked_edit(carousel_checked_list);
	}
			
	// [일괄삭제]
	public void carousel_checked_delete(List<Integer> carousel_num) {
		adminCarouselMapper.carousel_checked_delete(carousel_num);
	}
	
	
}
