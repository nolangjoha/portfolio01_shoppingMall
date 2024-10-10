package com.smilemall.basic.admin.analysischart;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnalysisChartService {

	private final AnalysisChartMapper analysisChartMapper;
	
	
	// <월간 일일매출 현황>
	public List<Map<String, Object>> dailySalesStatsByMonth(String ord_month) {
		return analysisChartMapper.dailySalesStatsByMonth(ord_month);
	};
	
	// <연간 월매출 현황>
	public List<Map<String, Object>> monthlySalesStatsByYear(String ord_year) {
		return analysisChartMapper.monthlySalesStatsByYear(ord_year);
	}
	
	
	
	// <월간 상위카테고리 매출 현황>
	public List<Map<String,Object>> monthlySalesStatusByTopCategory(String ord_date) {
		return analysisChartMapper.monthlySalesStatusByTopCategory(ord_date);
	}
	
	// <월간 하위카테고리 매출 현황>
	public List<Map<String,Object>> monthlySalesStatusBySubCategory(String ord_date) {
		return analysisChartMapper.monthlySalesStatusBySubCategory(ord_date);
	}
	
	// <연간 상위카테고리 매출 현황>
	public List<Map<String,Object>> yearlySalesStatusByTopCategory(String ord_year) {
		return analysisChartMapper.yearlySalesStatusByTopCategory(ord_year);
	}
		
	// <연간 하위카테고리 매출 현황>
	public List<Map<String,Object>> yearlySalesStatusBySubCategory(String ord_year) {
		return analysisChartMapper.yearlySalesStatusBySubCategory(ord_year);
	}
		
	
	
	// <월간 판매상품 통계>
	public List<Map<String, Object>> monthlyProductSalesRank(String ord_month) {
		return analysisChartMapper.monthlyProductSalesRank(ord_month);
	}
	
	// <연간 판매상품 통계>
	public List<Map<String, Object>> yearlyProductSalesRank(String ord_year) {
		return analysisChartMapper.yearlyProductSalesRank(ord_year);
	}
	
	
}
