package net.ezens.pointnix.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;
import net.ezens.pointnix.notice.NoticeService;
import net.ezens.pointnix.notice.model.Notice;
import net.ezens.pointnix.payload.PageRequest;
import net.ezens.pointnix.payload.PageWrapper;

@Log4j2
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	NoticeService service;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView noticeListPage(ModelAndView mav, Optional<PageRequest> pageRequest) {
		PageRequest request = pageRequest.orElse(new net.ezens.pointnix.payload.PageRequest());
		
		Page<Notice> noticePage = service.noticeList(request.of());
		PageWrapper<Notice> page = new PageWrapper<Notice>(noticePage, "/notice");
		
		mav.setViewName("notice/notice_main");
		mav.addObject("notice", noticePage);
		mav.addObject("page", page);
		
		return mav;
	}
}
