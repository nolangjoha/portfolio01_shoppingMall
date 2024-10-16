package com.smilemall.basic.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smilemall.basic.admin.analysischart.AnalysisChartService;
import com.smilemall.basic.admin.member.AdminMemberService;
import com.smilemall.basic.admin.order.AdminOrderService;
import com.smilemall.basic.admin.question.AdminQuestionService;
import com.smilemall.basic.admin.review.AdminReviewService;
import com.smilemall.basic.member.MemberVo;
import com.smilemall.basic.order.OrderVo;
import com.smilemall.basic.payinfo.PayInfoService;
import com.smilemall.basic.question.QuestionVo;
import com.smilemall.basic.review.ReviewVo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/*")
public class AdminController {

	private final AdminService adminService;
	private final PasswordEncoder passwordEncoder;
	
	private final AdminOrderService adminOrderService; // 주문정보
	private final AdminMemberService adminMemberService; // 회원정보
	private final AdminQuestionService adminQuestionService; // 문의정보
	private final AdminReviewService adminReviewService; // 리뷰정보
	private final AnalysisChartService analysisChartService; //통계
	
	
	// [관리자 로그인 페이지]
	@GetMapping("")
	public String loginForm() {
		return "/admin/adlogin";
	}
	
	// [관리자 메뉴 페이지]
	@GetMapping("/admin_menu")
	public void admin_menu(Model model) throws Exception {
		
		// [최근 주문내역]
		List<OrderVo> admin_ord_list = adminOrderService.admin_ord_list();
//		List<OrderVo> admin_ord_list = null;
		model.addAttribute("admin_ord_list", admin_ord_list);				
		
		// [최근 회원가입]
		List<MemberVo> admin_member_list = adminMemberService.admin_member_list();
//		List<MemberVo> admin_member_list = null;
		model.addAttribute("admin_member_list", admin_member_list);	
		
		// [최근 문의사항]
		List<QuestionVo> admin_question_list = adminQuestionService.admin_question_list();
		admin_question_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
//		List<QuestionVo> admin_question_list =  null;
		model.addAttribute("admin_question_list", admin_question_list);	
		
		// [최근 후기]
		List<ReviewVo> admin_rev_list = adminReviewService.admin_rev_list();
		admin_rev_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
//		List<ReviewVo> admin_rev_list = null;
		model.addAttribute("admin_rev_list", admin_rev_list);	
		
		// [통계]
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		String ord_month = String.format("%s/%s", year,(month < 10 ? "0" + String.valueOf(month) : month));
//		String ord_month = "2024/08";
		//String ord_month = "2024/09";
		//log.info("이번달:"+ ord_month);
		
		// 1.이번달 매출
		int adminPageMonthSales = analysisChartService.adminPageMonthSales(ord_month);
		//log.info("이번달 매출:"+ adminPageMonthSales);
		model.addAttribute("adminPageMonthSales", adminPageMonthSales);	
		
		// 2.이번달 카테고리별 매출
		List<Map<String,Object>> topCategorySalesData = analysisChartService.monthlySalesStatusByTopCategory(ord_month);
		//log.info("이번달 카테고리 매출:"+ topCategorySalesData);
		model.addAttribute("topCategorySalesData", topCategorySalesData);	
		
		// 3. 많이 판매된 상품
		List<Map<String, Object>> monthlyProductSalesRankData = analysisChartService.monthlyProductSalesRank(ord_month);
		//log.info("이번달 판매상품 :"+ monthlyProductSalesRankData);
		List<Map<String, Object>> top3ProductSalesRankData = monthlyProductSalesRankData.size() > 3 ? monthlyProductSalesRankData.subList(0, 3) : monthlyProductSalesRankData;
		//log.info("top3:" + top3ProductSalesRankData);
		model.addAttribute("top3ProductSalesRankData", top3ProductSalesRankData);	
	
	}
	
	// [관리자 로그인 기능]
	@PostMapping("/admin_ok")
	public String admin_ok(AdminVo vo, HttpSession session) throws Exception {
		
		//사용자가 입력한 아이디를 대입한다.
		AdminVo d_vo = adminService.loginOk(vo.getAdmin_id());
		
		String url = "";
		
		//사용자의 입력값이 DB에 있다면
		if(d_vo != null) {
			
			// DB에 저장된 비밀번호와 사용자가 입력한 비밀번호가 일치한다면
			if(passwordEncoder.matches(vo.getAdmin_pw(), d_vo.getAdmin_pw())) {
				
				//비밀번호니까 일단 지워준다.
				d_vo.setAdmin_pw("");
				// 사용자의 아이디에 세션값 부여
				session.setAttribute("admin_state", d_vo);
				
				url = "admin_menu";
			}
		}
		return "redirect:/admin/" + url;
	}
	
	// [관리자 페이지 로그아웃]
	@GetMapping("/admin_logout")
	public String admin_logout(HttpSession session) {
		
		//관리자 세션부분만 제거
		session.removeAttribute("admin_state");
		
		return "redirect:/admin/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
