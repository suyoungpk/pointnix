package net.ezens.pointnix.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;
import net.ezens.pointnix.payload.LoginRequest;

@Log4j2
@Controller
@RequestMapping("/login")
public class LoginController {
	
	// 로그인 페이지
	@PreAuthorize("permitAll")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView loginPage(@ModelAttribute("LoginRequest") LoginRequest loginRequest) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("login/login");
		return mav;
	}
}
