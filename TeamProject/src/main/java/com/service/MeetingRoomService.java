package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MeetingRoomDAO;
import com.dto.MeetingRoomDTO;
@Service("MeetingRoomService")
public class MeetingRoomService {
	
	@Autowired
	MeetingRoomDAO dao;
	
	public MeetingRoomService() {
		dao = new MeetingRoomDAO();
	}

	// 회의실 예약 
	public int reserve(MeetingRoomDTO dto) {
		int num = dao.reserve(dto);
		return num;
	}

	public List<MeetingRoomDTO> select(int memberNum) {
		List<MeetingRoomDTO> list = dao.select(memberNum);
		return list;
	}

	public int delete(MeetingRoomDTO dtoM) {
		int num = dao.delete(dtoM);
		return num;
	}

	
	
}
