package net.ezens.pointnix.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cloud")
@PreAuthorize("isAuthenticated()")
public class CloudController {
	// 주문의뢰 화면
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView order(ModelAndView mav) {
		mav.setViewName("cloud/cloud_order");
		return mav;
	}
	
	
	// 주문의뢰 저장
	
	// 주문의뢰 현황 목록 화면
	
	// 주문의뢰 현황 상세 화면(접수 상태가 아닐 때)
	
	// 주문의뢰 현황 수정 화면(접수 상태일 때)
	
}
