package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dao.NotificationDAO;
import com.dto.MemberDTO;
import com.dto.NotificationDTO;

@Service
public class NotificationService {
	@Autowired
	NotificationDAO dao;
	public void saveNotification(NotificationDTO notificationDTO) {
		dao.saveNotification(notificationDTO);
		
	}
//	public int countNotification(int member_num) {
//		List<NotificationDTO> notReadingNotiList = dao.countNotification(member_num);
//		int noti_count = notReadingNotiList.size();
//		return noti_count;
//	}
	public List<NotificationDTO> notiListNotReading(HttpSession session) {
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
		int member_num = memberDTO.getMember_num();
		
		List<NotificationDTO> notiList = dao.notiListNotReading(member_num);
		session.setAttribute("notiList", notiList);
		return notiList;
	}
	public void deleteNotification(int noti_num) {
		dao.deleteNotification(noti_num);
		
	}
	
}