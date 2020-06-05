package net.ezens.pointnix.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;
import net.ezens.pointnix.member.model.Role;

@Log4j2
@Controller
@RequestMapping("/")
public class CommonController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		// 로그인 여부
		if(principal != null) {
			log.info("principal : {}", principal);
			mav.setViewName("redirect:/home");
		} else {
			mav.setViewName("redirect:/login");
		}
		return mav;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signUpPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("layout/default");
		return mav;
	}
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView calendarPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calendar/calendar");
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		
		log.info("enter home");
		
		// 권한별 redirect
		if(req.isUserInRole(Role.ADMIN.getValue())) {
			mav.setViewName("redirect:/notice");
		} else if(req.isUserInRole(Role.MEMBER.getValue())) {
			mav.setViewName("redirect:/cloud");
		} else {
			mav.setViewName("redirect:/notice");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView deniedPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/error");
		return mav;
	}
}
