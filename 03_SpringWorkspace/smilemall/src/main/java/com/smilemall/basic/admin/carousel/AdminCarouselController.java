package com.smilemall.basic.admin.carousel;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.common.util.FileManagerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/carousel/*")
public class AdminCarouselController {

	private final AdminCarouselService adminCarouselService;
	
	// 캐러셀 이미지 업로드 경로
	@Value("${file.carousel.image.dir}")
	private String uploadPath;
	
	
	//[캐러셀 등록폼 페이지]
	@GetMapping("/carousel_insert")
	public void carousel_insertForm() {
		//log.info("케러셀 등록폼 페이지");
	}
	
	// [캐러셀 등록]
	@PostMapping("/carousel_insert")
	public String carousel_insert(CarouselVo vo, MultipartFile uploadFile) throws Exception {
		
		String dateFolder = FileManagerUtils.getDateFolder();
		String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setCarousel_img(saveFileName);
		vo.setCarousel_up_folder(dateFolder);
		
		//log.info("캐러셀 정보" + vo);
		
		adminCarouselService.carousel_insert(vo);
		
		return "redirect:/admin/carousel/carousel_list";
	}
	
	// [캐러셀 목록]
	@GetMapping("/carousel_list")
	public void carousel_list(Criteria cri, Model model, @ModelAttribute("carousel_view_status") String carousel_view_status, 
			@ModelAttribute("start_date") String start_date, @ModelAttribute("end_date") String end_date) throws Exception {
		
		// 표시 목록 개수
		cri.setAmount(Constants.AMIN_CAROUSEL_LIST_AMOUNT);
		
		log.info("파라미터값" + cri);
		log.info("파라미터값" + carousel_view_status);
		log.info("파라미터값" + start_date);
		log.info("파라미터값" + end_date);
		
		// 목록정보
		List<CarouselVo> carousel_list = adminCarouselService.carousel_list(cri, carousel_view_status, start_date, end_date);
		
		// 이미지 폴더 구분자 변환
		carousel_list.forEach(vo -> {
			vo.setCarousel_up_folder(vo.getCarousel_up_folder().replace("\\", "/"));
		});
		
		int totalCount = adminCarouselService.getTotalCount(cri, carousel_view_status, start_date, end_date);
		
		model.addAttribute("carousel_list", carousel_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	// <상품리스트에서 사용할 이미지 보여주기>
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	// [캐러셀 수정 페이지]
	@GetMapping("/carousel_edit")
	public void carousel_edit(@ModelAttribute("cri") Criteria cri, Integer carousel_num, Model model ) throws Exception {
		//log.info("수정데이터 번호 :" + carousel_num);
		
		// 수정데이터
		CarouselVo vo = adminCarouselService.carousel_edit_data(carousel_num);
		
		// 경로 구분자 변환
		vo.setCarousel_up_folder(vo.getCarousel_up_folder().replace("\\", "/"));
		model.addAttribute("CarouselVo", vo);
	
		//log.info("수정데이터 :" + vo);
	}
	
	// [캐러셀 수정]
	@PostMapping("/carousel_edit")
	public String carousel_edit(CarouselVo vo, MultipartFile uploadFile, Criteria cri, RedirectAttributes rttr) throws Exception {
		
		// 배너이미지 변경시
		if(!uploadFile.isEmpty()) {
			
			FileManagerUtils.delete(uploadPath, vo.getCarousel_up_folder(), vo.getCarousel_img(), "image");
		
			String dateFolder = FileManagerUtils.getDateFolder();
			String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
			
			vo.setCarousel_img(saveFileName);
			vo.setCarousel_up_folder(dateFolder);
		}
		
		// 데이터 수정
		adminCarouselService.carousel_edit(vo);
		
		return "redirect:/admin/carousel/carousel_list" + cri.getListLink();
	}
	
	// [캐러셀 삭제]
	@PostMapping("/carousel_delete")
	public String carousel_delete(Integer carousel_num, Criteria cri) throws Exception {
		
		adminCarouselService.carousel_delete(carousel_num);
		return "redirect:/admin/carousel/carousel_list" + cri.getListLink();
	}
	
	// [게시상태 일괄수정]
	@PostMapping("/carousel_checked_edit")
	public ResponseEntity<String> carousel_checked_edit(
			@RequestParam("carousel_num_arr") List<Integer> carousel_num_arr,
			@RequestParam("carousel_view_status_arr") List<String> carousel_view_status_arr
			) throws Exception {
		
		adminCarouselService.carousel_checked_edit(carousel_num_arr, carousel_view_status_arr);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<> ("success", HttpStatus.OK);
		
		return entity;
	}
	
	// [일괄 삭제]
	@PostMapping("/carousel_checked_delete")
	public ResponseEntity<String> carousel_checked_delete(@RequestParam("carousel_num") List<Integer> carousel_num) throws Exception {
		
		adminCarouselService.carousel_checked_delete(carousel_num);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<> ("success", HttpStatus.OK);
	
		return entity;
	}
	
	
}
