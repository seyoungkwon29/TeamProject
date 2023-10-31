package com.controller.notification;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		NotificationDTO notificationDTO = new NotificationDTO(member_num,content);
		service.saveNotification(notificationDTO);
		return "";
	}
	
	@RequestMapping(value = "deleteNotification", method = RequestMethod.GET)
	@ResponseBody
	public String deleteNotification(@RequestParam int noti_num) {
		service.deleteNotification(noti_num);
		return "삭제완료";
	}
	

//	@RequestMapping(value = "countNotification", method = RequestMethod.GET)
//	@ResponseBody
//	public int countNotification(int member_num) {
//		int noti_count = service.countNotification(member_num);
//		System.out.println("안읽은 메일 갯수 : " + noti_count);
//		return noti_count;
//	}
	
	@RequestMapping(value = "notiListNotReading", method = RequestMethod.GET)
	@ResponseBody
	public List<NotificationDTO> notReadingNotification(HttpSession session) {
		List<NotificationDTO> notiListNotReading = service.notiListNotReading(session);
		return notiListNotReading;
	}
	
	@RequestMapping("/notiTest")
	public String notiTest() {
		return "notiTest";
	}
}

