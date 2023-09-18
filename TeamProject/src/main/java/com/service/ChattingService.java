package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChattingDAO;
import com.dto.ChatMemberDTO;
import com.dto.ChatRoomDTO;
import com.dto.MemberDTO;
@Service("ChattingService")
public class ChattingService {
	
	@Autowired
	ChattingDAO dao;
	
	public ChattingService() {
		dao = new ChattingDAO();
	}
	
	public List<MemberDTO> selectMembersExceptMe(int member_num) {
		List<MemberDTO> list = dao.selectMembersExceptMe(member_num);
		return list;
	}
	
	public List<MemberDTO> searchValue(Map<String, String> map) {
		List<MemberDTO> list = dao.searchValue(map);
		return list;
	}

	public int createChatRoom(ChatRoomDTO chatRoom) {
		int result = dao.createChatRoom(chatRoom);
		return result;
	}

	public int registor(int member_num) {
		int num = dao.registor(member_num);
		return num;
	}

	public List<ChatMemberDTO> selectAllMember(ChatRoomDTO chatRoom) {
		List<ChatMemberDTO> list = dao.selectAllMember(chatRoom);
		return list;
	}

}
