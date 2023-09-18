package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MeetingRoomDTO;
import com.dto.MemberDTO;
@Repository("MeetingRoomDAO")
public class MeetingRoomDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	// 회의실 예약하기
	public int reserve(MeetingRoomDTO dto) {
		int num = session.insert("reserve",dto);
		return num;
	}

	public List<MeetingRoomDTO> select(int memberNum) {
		List<MeetingRoomDTO> list = session.selectList("select", memberNum);
		return list;
	}

	public int delete(MeetingRoomDTO dtoM) {
		int num = session.update("delete",dtoM);
		return num;
	}

}
