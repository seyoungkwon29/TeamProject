package com.controller.notification;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.NotificationDTO;
import com.service.NotificationService;

@Controller
public class NotificationController {
	@Autowired
	NotificationService service;
	
	
	@RequestMapping(value = "saveNotification", method = RequestMethod.POST)
	@ResponseBody
	public String saveNotification(@RequestBody Map<String,Object> parameters) {
		int member_num = (int)parameters.get("member_num");
		String content = (String)parameters.get("msg");
		service.saveNotification(member_num, content);
		return "";
	}

	@RequestMapping(value = "countNotification", method = RequestMethod.GET)
	public int countNotification(int member_num) {
		int noti_count = service.countNotification(member_num);
		System.out.println("안읽은 메일 갯수 : " + noti_count);
		return noti_count;
	}
	
	@RequestMapping(value = "notReadingNotification", method = RequestMethod.GET)
	public List<NotificationDTO> notReadingNotification(int member_num) {
		List<NotificationDTO> list = service.notReadingNotification(member_num);
		return list;
	}
	
	@RequestMapping("/notiTest")
	public String notiTest() {
		return "notiTest";
	}
}