package com.smilemall.basic.admin.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smilemall.basic.admin.category.AdminCategoryService;
import com.smilemall.basic.admin.category.CategoryVo;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.common.util.FileManagerUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/product/*")
public class AdminProductController {

	private final AdminProductService adminProductService;
	
	private final AdminCategoryService adminCategoryService;
	
	// 상품이미지 업로드 경로
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	
	// CKEditor파일 업로드 경로
	@Value("${file.ckeditor}")
	private String uploadCKpath;
	
	
	//[상픔등록폼 페이지]
	@GetMapping("pro_insert")
	public void pro_insertForm(Model model) {
		List<CategoryVo> cate_list = adminCategoryService.getFirstCategoryList();
		model.addAttribute("cate_list", cate_list);
	}
	
	// [상품등록 기능]
	@PostMapping("pro_insert")
	public String pro_insertOk(ProductVo vo, MultipartFile uploadFile) throws Exception {
		
		// 1)상품 이미지 업로드
		String dateFolder = FileManagerUtils.getDateFolder();
		String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setPro_img(saveFileName);
		vo.setPro_up_folder(dateFolder);
		
		log.info("상품정보" + vo);
		
		
		//2) 상품정보 DB저장
		adminProductService.pro_insert(vo);
		
		return "redirect:/admin/product/pro_insert"; // pro_list만들면 주소 변경
	}
	
	
	// <CKEditor 상품설명 이미지 업로드>
	@PostMapping("/ckeditor_image_upload")
	public void ckeditor_image_upload (HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null;  // 서버에서 클라이언트에게 응답정보를 보낼 때 사용. //업로드한 이미지 정보를 브라우저에게 보낸다.
 		

		try {
			//1) CKEditor를 이용한 업로드 처리
			String fileName = upload.getOriginalFilename(); //업로드 파일의 이름을 가져옴.
			byte[] bytes = upload.getBytes();  				//업로드할 파일의 바이트 배열
			
			String ckUploadPath = uploadCKpath + fileName;  // 업로드 파일이 저장되는 경로
			
			out = new FileOutputStream(ckUploadPath);   	//output스트림 객체 생성
			
			out.write(bytes);  //스트림에 업로드할 파일의 바이트 배열을 채움.
			out.flush();  // 출력
			
			
			//2) 업로드한 이미지 파일에 대한 정보를 클라이언트에게 보내는 작업
			printWriter = response.getWriter();
			
			String fileUrl = "/admin/product/display/" + fileName;
			
			printWriter.println("{\"filename\" :\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");  // 스트림에 채움 // 사용법 약속, 공식사이트에 있음.
			printWriter.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}	
	}
	
	
	// <CKEditor로 업로드한 이미지를 다시 에디터에 출력될 수 있도록 하는 작업>
	@GetMapping("/display/{fileName}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			entity = FileManagerUtils.getFile(uploadCKpath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	
	// [상품리스트]
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri, Model model) throws Exception {
		

		
		//DB에서 상품데이터 가져오기
		List<ProductVo> pro_list = adminProductService.pro_list(cri);
		
		//이미지 폴더 구분자 변환
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		// 상품데이터 총합
		int totalCount = adminProductService.getTotalCount(cri);
		
		model.addAttribute("pro_list", pro_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	
	// <상품리스트에서 사용할 이미지 보여주기>
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	
	// [상품 수정 페이지] : 수정할 데이터 불러오기
	@GetMapping("/pro_edit")
	public void pro_edit(@ModelAttribute("cri") Criteria cri, Integer pro_num, Model model) throws Exception {
		log.info("수정할 상품번호: " + pro_num);
		
		// 1차 카테고리 목록 출력
		List<CategoryVo> cate_list = adminCategoryService.getFirstCategoryList();
		model.addAttribute("cate_list", cate_list);
		
		// 수정할 데이터 불러옴. / 2차 카테고리 코드 있음.
		ProductVo vo = adminProductService.pro_edit(pro_num);
		
		//이미지 폴더 구분자 전환
		vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		model.addAttribute("productVo", vo);
		
		// 상품테이블에 존재하는 2차 카테고리 코드
		int cat_code = vo.getCat_code(); 
		// 1차 카테고리 코드
		int cat_prtcode = adminCategoryService.getFirstCategoryBySecondCategory(cat_code).getCat_prtcode();
		model.addAttribute("cat_prtcode", cat_prtcode);
		
		//2차 카테고리 목록
		model.addAttribute("sub_cate_list", adminCategoryService.getSecondCategoryList(cat_prtcode));
		
	}
	
	
	// [상품 수정 기능] : 수정한 데이터 DB로 보냄.
	@PostMapping("/pro_edit")
	public String pro_edit_ok(ProductVo vo, MultipartFile uploadFile, Criteria cri, RedirectAttributes rttr) {
		
		//상품 이미지 변경 시
		if(!uploadFile.isEmpty()) {
			
			//1) 기존 상품이미지 삭제 // 매개변수: 업로드 경로, 업로드 했던 폴더, 업로드 했던 이미지파일 이름, 파일 타입
			FileManagerUtils.delete(uploadPath, vo.getPro_up_folder(), vo.getPro_img(), "image");
			
			//2)변경이미지 업로드
			String dateFolder = FileManagerUtils.getDateFolder();
			String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
			
			// 새로운 파일명과 날짜 폴더명을 지정
			vo.setPro_img(saveFileName);
			vo.setPro_up_folder(dateFolder);
			
		}
		
		
		// DB수정(업데잍)
		adminProductService.pro_edit_ok(vo);
		
		log.info("cri페이징: " + cri);
		
		return "redirect:/admin/product/pro_list"+ cri.getListLink();
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
}

