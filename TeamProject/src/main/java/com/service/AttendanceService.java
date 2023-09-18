package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AttendanceDAO;
import com.dto.AttendanceDTO;

@Service("AttendanceService")
public class AttendanceService {
	@Autowired
	AttendanceDAO dao;

	public AttendanceService() {
		dao = new AttendanceDAO();
	}

	// 출근
	public int punchIn(AttendanceDTO attendance) {
		int att = dao.punchIn(attendance);
		return att;
	}

	// 퇴근
	public int punchOut(AttendanceDTO attendance) {
		int att = dao.punchOut(attendance);
		return att;
	}
	
	// 근무 기록 리스트
	public List<AttendanceDTO> viewAttList(String memNum) {
		List<AttendanceDTO> list = dao.viewAttList(memNum);
		return list;
	}

}
