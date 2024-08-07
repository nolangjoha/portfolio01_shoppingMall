package com.smilemall.basic.payinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payinfo/*")
public class PayInfoController {
	
	private final PayInfoService payInfoService;
	
}
