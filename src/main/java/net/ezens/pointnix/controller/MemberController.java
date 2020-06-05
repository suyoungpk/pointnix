package net.ezens.pointnix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.ezens.pointnix.member.MemberService;
import net.ezens.pointnix.member.model.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 유저 목록 페이지
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView memberListPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("title", "hello!");
		return mav;
	}
	
	// 유저 목록 불러오기 + 페이징
	
	// 유저 생성 페이지
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView signUpPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/join");
		return mav;
	}
	
	// 유저 생성
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView signUp(Member member) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(member.toString());
		
		memberService.joinUser(member);
		
		mav.setViewName("redirect:/layout/default");
		return mav;
	}
	
	// 유저 수정 페이지
	
	// 유저 수정
	
	
}
