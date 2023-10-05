package com.controller.wbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.WbsService;

@Controller
public class WbsController {

	@Autowired
	WbsService wService;
	
	@RequestMapping(value = "calendar")
	public String calendar() {
		System.out.println("캘린더 호출");
		return "calendar/calendar";
	}
	
	
}
