package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.NotificationDTO;

@Repository
public class NotificationDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public void saveNotification(NotificationDTO notiDTO) {
		int res = session.insert("saveNotification",notiDTO);
//		System.out.println("결과값 : "+res);
	}
	public List<NotificationDTO> countNotification(int member_num) {
		List<NotificationDTO> list = session.selectList("countNotification",member_num);
		return list;
	}
	public void deleteNotification(int noti_num) {
		int res = session.delete("deleteNotification",noti_num);
//		System.out.println("삭제 확인 : " + res);
	}
	
}

