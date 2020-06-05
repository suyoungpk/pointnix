package net.ezens.pointnix.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CustomErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH)
	public ModelAndView handleError(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		log.info("httpStatus : {}", httpStatus.toString());
		mav.addObject("code", status.toString());
		mav.addObject("msg", httpStatus.getReasonPhrase());
		mav.addObject("timestamp", new Date());
		mav.setViewName("error/error");

		return mav;
	}
}
