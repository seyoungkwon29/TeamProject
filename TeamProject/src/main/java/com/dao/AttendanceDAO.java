package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AttendanceDTO;

@Repository("AttendanceDAO")
public class AttendanceDAO {
	@Autowired
	SqlSessionTemplate session;

	// 출근
	public int punchIn(AttendanceDTO attendance) {
		int att = session.update("punchIn", attendance);
		return att;
	}
	
	public int punchOut(AttendanceDTO attendance) {
		int att = session.update("punchOut", attendance);
		return att;
	}

	// 근무 기록 리스트
	public List<AttendanceDTO> viewAttList(String memNum) {
		List<AttendanceDTO> list = session.selectList("viewAttList", memNum);
		return list;
	}

}
