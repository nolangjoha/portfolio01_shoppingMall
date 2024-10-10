package com.smilemall.basic.admin.analysischart;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/analysischart/*")
public class AnalysisChartController {

	private final AnalysisChartService analysisChartService;
	
	
	// 매출통계 페이지
	@GetMapping("/sales_stats")
	public void sales_stats(Model model) {
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
	}
	
	// <월별 일일 매출 통계 데이터 조회>
	@GetMapping("/dailySalesStatusByMonth")
	@ResponseBody
	public List<Map<String, Object>> dailySalesStatusByMonth(int year, int month) {
		String ord_month = String.format("%s/%s", year,(month < 10 ? "0" + String.valueOf(month) : month));
		log.info("일일매출 검색월: " + ord_month);
	
		List<Map<String, Object>> dailySalesData = analysisChartService.dailySalesStatsByMonth(ord_month);
		
		return dailySalesData;
	}
	
	// <연도별 월매출 통계 데이터 조회>
	@GetMapping("/monthlySalesStatsByYear")
	@ResponseBody
	public List<Map<String, Object>> monthlySalesStatsByYear(int year) {
		String ord_year = String.format("%s", year);
		log.info("월매출 검색연도: " + ord_year);
	
		List<Map<String, Object>> monthlySalesData = analysisChartService.monthlySalesStatsByYear(ord_year);
		
		return monthlySalesData;
	}

	
	
	// 카테고리별 매출현황 페이지
	@GetMapping("/categories_stats")
	public void categories_stats(Model model) {
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
	}
	
	// <월간 카테고리 매출 현황>
	@GetMapping("/monthlySalesStatusByCategories")
	@ResponseBody
	public Map<String,Object> monthlySalesStatusByCategories(int year, int month) {
		
		String ord_date = String.format("%s/%s", year,(month < 10 ? "0" + String.valueOf(month) : month));
		log.info("선택날짜: " + ord_date);
		
		// <월간 상위카테고리 매출 현황>
		List<Map<String,Object>> topCategorySalesData = analysisChartService.monthlySalesStatusByTopCategory(ord_date);
		
		// <월간 하위카테고리 매출 현황>
		List<Map<String,Object>> subCategorySalesData = analysisChartService.monthlySalesStatusBySubCategory(ord_date);
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("topCategorySalesData", topCategorySalesData);
		resultMap.put("subCategorySalesData", subCategorySalesData);
		
		return resultMap;
	}
	
	// <연간 카테고리 매출 현황>
	@GetMapping("/yearlySalesStatusByCategories")
	@ResponseBody
	public Map<String,Object> yearlySalesStatusByCategories(int year) {
		
		String ord_year = String.format("%s", year);
		log.info("선택연도: " + ord_year);
		
		// <연간 상위카테고리 매출 현황>
		List<Map<String,Object>> yearlytopCategorySalesData = analysisChartService.yearlySalesStatusByTopCategory(ord_year);
		
		// <연간 하위카테고리 매출 현황>
		List<Map<String,Object>> yearlysubCategorySalesData = analysisChartService.yearlySalesStatusBySubCategory(ord_year);
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("yearlytopCategorySalesData", yearlytopCategorySalesData);
		resultMap.put("yearlysubCategorySalesData", yearlysubCategorySalesData);
		
		return resultMap;
	}

	
	
	// 제품판매통계 페이지
	@GetMapping("/product_sales_stats")
	public void product_sales_stats(Model model) {
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
	}
		
	// <월간 판매상품 통계>
	@GetMapping("/monthlyProductSalesRank")
	@ResponseBody
	public List<Map<String, Object>> monthlyProductSalesRank(int year, int month) {
		String ord_month = String.format("%s/%s", year,(month < 10 ? "0" + String.valueOf(month) : month));
		log.info("월간 판매상품 검색월: " + ord_month);
	
		List<Map<String, Object>> monthlyProductSalesRankData = analysisChartService.monthlyProductSalesRank(ord_month);
		
		return monthlyProductSalesRankData;
	}
	
	// <연간 판매상품 통계>
	@GetMapping("/yearlyProductSalesRank")
	@ResponseBody
	public List<Map<String, Object>> yearlyProductSalesRank(int year) {
		String ord_year = String.format("%s", year);
		log.info("연간 판매상품 검색월: " + ord_year);
	
		List<Map<String, Object>> yearlyProductSalesRankData = analysisChartService.yearlyProductSalesRank(ord_year);
		
		return yearlyProductSalesRankData;
	}
	
	
}
