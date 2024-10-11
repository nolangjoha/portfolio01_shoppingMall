package com.smilemall.basic.admin.carousel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smilemall.basic.common.dto.Criteria;

public interface AdminCarouselMapper {

	//[캐러셀 등록]
	void carousel_insert(CarouselVo vo);
	
	// [캐러셀 목록]
	//List<CarouselVo> carousel_list (Criteria cri);
	List<CarouselVo> carousel_list (@Param("cri") Criteria cri, @Param("carousel_view_status") String carousel_view_status,
			@Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// [전체 데이터 개수]
	//int getTotalCount(Criteria cri);
	int getTotalCount(@Param("cri") Criteria cri, @Param("carousel_view_status") String carousel_view_status,
			@Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// [메인페이지 캐러셀 출력]
	List<CarouselVo> carousel_post_list ();
	
	// [캐러셀 수정 데이터 불러오기]
	CarouselVo carousel_edit_data(Integer carousel_num);
	
	// [캐러셀 수정]
	void carousel_edit (CarouselVo vo);
	
	// [캐러셀 삭제]
	void carousel_delete(Integer carousel_num);
	
	// [게시상태 일괄수정]
	void carousel_checked_edit (List<CarouselDTO> carousel_checked_list);
		
	// [일괄삭제]
	void carousel_checked_delete(List<Integer> carousel_num);
	
}
