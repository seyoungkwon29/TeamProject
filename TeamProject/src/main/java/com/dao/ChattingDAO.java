package com.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ChatMemberDTO;
import com.dto.ChatRoomDTO;
import com.dto.MemberDTO;
@Repository("ChattingDAO")
public class ChattingDAO {
	
	@Autowired
	SqlSessionTemplate session;
	


	public List<MemberDTO> searchValue(Map<String, String> map) {
		List<MemberDTO> list = session.selectList("searchValue", map);
		return list;
	}

	public int createChatRoom(ChatRoomDTO chatRoom) {
		int result = session.insert("createChatRoom", chatRoom);
		return result;
	}

	public int registor(int member_num) {
		int num = session.insert("registor",member_num);
		return num;
	}

	public List<ChatMemberDTO> selectAllMember(ChatRoomDTO chatRoom) {
		List<ChatMemberDTO> list = session.selectList("selectAllMember",chatRoom);
		return list;
	}

	public List<MemberDTO> selectMembersExceptMe(int member_num) {
		List<MemberDTO> list = session.selectList("selectMembersExceptMe",member_num);
		return list;
	}
	
}
