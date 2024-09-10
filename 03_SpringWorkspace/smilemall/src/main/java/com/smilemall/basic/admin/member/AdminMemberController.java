package com.smilemall.basic.admin.member;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smilemall.basic.common.constants.Constants;
import com.smilemall.basic.common.dto.Criteria;
import com.smilemall.basic.common.dto.PageDTO;
import com.smilemall.basic.common.util.FileManagerUtils;
import com.smilemall.basic.mail.EmailDTO;
import com.smilemall.basic.mail.EmailService;
import com.smilemall.basic.member.MemberVo;
import com.smilemall.basic.order.OrderVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
public class AdminMemberController {

	private final AdminMemberService adminMemberService;
	private final EmailService emailService;
	
	// CKEditor파일 업로드 경로
	@Value("${file.ckeditor}")
	private String uploadCKpath;
	
	
	// [회원목록]
	@GetMapping("/member_list")
	public void member_list(Criteria cri, Model model) throws Exception {
		
		cri.setAmount(Constants.AMIN_MEMBER_LIST_AMOUNT);
		log.info("cri정보 :" + cri);
		
		List<MemberVo> member_list = adminMemberService.member_list(cri);
		
		int totalCount = adminMemberService.getTotalCount(cri);
		
		model.addAttribute("member_list", member_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}

	// [회원상세정보]
	@GetMapping("/member_info")
	public ResponseEntity<Map<String, Object>> member_info(String mbsp_id, Model model) throws Exception {
		log.info("수정회원 아이디: " + mbsp_id);
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		MemberVo vo = adminMemberService.member_info(mbsp_id);
		map.put("member", vo);
		
		entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
	// [회원상세정보 수정하기]
	@PostMapping("/member_modify")
	public ResponseEntity<String> member_modify(MemberVo vo) throws Exception {
		log.info("수정정보: "+ vo);
	
		ResponseEntity<String> entity = null;
		adminMemberService.member_modify(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	// [회원정보 삭제하기]
	@PostMapping("/member_delete")
	public String member_delete(Criteria cri, @RequestParam("mbsp_id") String mbsp_id) throws Exception {
		log.info("삭제아이디 : " +  mbsp_id);
		adminMemberService.member_delete(mbsp_id);
		
		return "redirect:/admin/member/member_list"+ cri.getListLink();
	}
	
	// [회원정보 일괄삭제]
	@PostMapping("/member_checked_delete")
	public ResponseEntity<String> member_checked_delete(@RequestParam("mbsp_id") List<String>mbsp_id) throws Exception {
		adminMemberService.member_checked_delete(mbsp_id);
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<> ("success", HttpStatus.OK);
		
		return entity;
	}
	
	// [회원주문목록]
	@GetMapping("/member_ord_info_list")
	public ResponseEntity<Map<String, Object>> member_ord_info_list(String mbsp_id) throws Exception {
		log.info("주문목록조회 아이디: " + mbsp_id);
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		//선택한 회원의 주문목록
		List<OrderVo> member_ord_info_list = adminMemberService.member_ord_info_list(mbsp_id);
		map.put("member_ord_info_list", member_ord_info_list);
		
		//주문목록 금액 합계
		int OrderPriceTotal = adminMemberService.OrderPriceTotal(mbsp_id);
		map.put("OrderPriceTotal", OrderPriceTotal);
		
		//주문목록 총건수
		int getTotalCountOrderList = adminMemberService.getTotalCountOrderList(mbsp_id);
		map.put("getTotalCountOrderList", getTotalCountOrderList);
		
		entity = new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
	// [메일발송폼]
	@GetMapping("/mailing_form")
	public void mailing_form() throws Exception {
		
	}
	
	// [메일저장]
	@PostMapping("/mailing_save")
	public String mailing_save(MailingVo vo, Model model, RedirectAttributes rttr) throws Exception {
		log.info("메일내용:" + vo);
		
		//메일저장
		adminMemberService.mailing_save(vo);
		
		log.info("시퀀스확인:" + vo.getMail_idx());
		
		// 메일발송횟수에서 사용
		model.addAttribute("mail_idx", vo.getMail_idx());
		
		rttr.addFlashAttribute("msg", "save");

		return "redirect:/admin/member/mailing_list";
	}
	
	// [메일목록]
	@GetMapping("/mailing_list")
	public void mailing_list(Criteria cri, Model model) throws Exception {
		
		// 메일링목록 출력개수
		cri.setAmount(Constants.AMIN_MAILLING_LIST_AMOUNT);	
		
		List<MailingVo> mailing_list = adminMemberService.mailing_list(cri);
		
		int mailingTotalCount = adminMemberService.getMailingTotalCount(cri);
		PageDTO pageDTO = new PageDTO(cri, mailingTotalCount);
		
		model.addAttribute("mailing_list", mailing_list);
		model.addAttribute("pageMaker", pageDTO);
	}
	
	// [저장메일정보]
	@GetMapping("/mailing_save_info")
	public void mailing_save_info(int mail_idx, Model model) throws Exception {
		MailingVo vo = adminMemberService.mailing_save_info(mail_idx);
		model.addAttribute("vo", vo);
	}
	
	// [저장메일 수정]
	@PostMapping("/mailing_modify")
	public String mailing_modify(@ModelAttribute("vo") MailingVo vo, Model model) throws Exception {
		adminMemberService.mailing_modify(vo);
		model.addAttribute("msg", "modify");
		return "/admin/member/mailing_save_info";
	}
	
	// [회원정보 삭제하기]
	@PostMapping("/mailing_delete")
	public String mailing_delete(Criteria cri, @RequestParam("mail_idx") int mail_idx) throws Exception {
		log.info("삭제메일링 : " +  mail_idx);
		adminMemberService.mailing_delete(mail_idx);
		
		return "redirect:/admin/member/mailing_list"+ cri.getListLink();
	}	
	
	// [메일발송]
	@PostMapping("/mailing_send") 
	public String mailing_send(MailingVo vo, RedirectAttributes rttr) throws Exception {
		 log.info("메일내용"+vo);
		
		 // 1) 마케팅 수신동의 회원목록 메일정보 읽어오기
		 String[] all_mail_address_arr = adminMemberService.all_mail_address();
		 
		 EmailDTO dto = new EmailDTO("SmileMall", "SmileMall", "", vo.getMail_title(), vo.getMail_content());
		 
		 // 2) 메일 발송
		 emailService.group_sendMail(dto, all_mail_address_arr);
				 
		 // 3) 메일 발송 횟수 업데이트
		 adminMemberService.mailSendCountUpdate(vo.getMail_idx());
		 
		 rttr.addFlashAttribute("msg", "send");
		 
		 return "redirect:/admin/member/mailing_list";
	}
	
	// <CKEditor 이메일 이미지 업로드>
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
			
			String fileUrl = "/admin/member/display/" + fileName;
			
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
	
}
