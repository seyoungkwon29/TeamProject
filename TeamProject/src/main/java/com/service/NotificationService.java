package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dao.NotificationDAO;
import com.dto.NotificationDTO;

@Service
public class NotificationService {
	@Autowired
	NotificationDAO dao;
	public void saveNotification(int member_num, String content) {
		NotificationDTO notiDTO = new NotificationDTO(member_num, content);
		dao.saveNotification(notiDTO);
		
	}
	public int countNotification(int member_num) {
		List<NotificationDTO> notReadingNotiList = dao.countNotification(member_num);
		int noti_count = notReadingNotiList.size();
		return noti_count;
	}
	public List<NotificationDTO> notReadingNotification(int member_num) {
		List<NotificationDTO> notReadingNotiList = dao.countNotification(member_num);
		return notReadingNotiList;
	}
	
}
