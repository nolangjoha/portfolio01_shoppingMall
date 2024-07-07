package com.smilemall.basic.admin.category;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminCategoryController {

	private final AdminCategoryService adminCategoryService;
	
}
