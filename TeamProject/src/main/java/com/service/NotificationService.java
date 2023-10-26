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
	public void saveNotification(NotificationDTO notificationDTO) {
		dao.saveNotification(notificationDTO);
		
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
	public void deleteNotification(int noti_num) {
		dao.deleteNotification(noti_num);
		
	}
	
}