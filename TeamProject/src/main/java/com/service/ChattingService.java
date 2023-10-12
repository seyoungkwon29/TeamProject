package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ChattingDAO;
import com.dto.ChatContentDTO;
import com.dto.ChatMemberDTO;
import com.dto.ChatRoomDTO;
import com.dto.ExtendedChatContentDTO;
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

	public int selectOnechatRoomNum() {
		int num = dao.selectOnechatRoomNum();
		return num;
	}

	public List<ChatRoomDTO> chatList(int member_num) {
		List<ChatRoomDTO> list = dao.chatList(member_num);
		return list;
	}

	public int registerChatContent(ChatContentDTO chatContent) {
		int num = dao.registerChatContent(chatContent);
		return num;
	}

	public ChatContentDTO printChatContent(int chatroom_num) {
		ChatContentDTO contentDTO = dao.printChatContent(chatroom_num);
		return contentDTO;
	}


	public int countMember(int chatroom_num) {
		int num = dao.countMember(chatroom_num);
		return num;
	}

	public ChatRoomDTO searchRoom(int chatroom_num) {
		ChatRoomDTO chatroom = dao.searchRoom(chatroom_num);
		return chatroom;
	}

	public ChatContentDTO createdRoomContent(int chatroom_num) {
		ChatContentDTO dto = dao.createdRoomContent(chatroom_num);
		return dto;
	}

	public List<ExtendedChatContentDTO> myContentsList(int chatroom_num) {
		List<ExtendedChatContentDTO> list = dao.myContentsList(chatroom_num);
		return list;
	}

	public List<ChatContentDTO> latestChatList(int member_num) {
		List<ChatContentDTO> list = dao.latestChatList(member_num);
		return list;
	}

	public int updateTitle(Map<String, Object> map) {
		int num = dao.updateTitle(map);
		return num;
	}

	public List<ChatMemberDTO> inviteMemberList(Map<String, Integer> map) {
		List<ChatMemberDTO> list = dao.inviteMemberList(map);
		return list;
	}

	public int inviteMember(Map<String, Integer> map) {
		int num = dao.inviteMember(map);
		return num;
	}

	public int updateMemberStatus(Map<String, Integer> map) {
		int num = dao.updateMemberStatus(map);
		return num;
	}

	public void deleteChatRoom(int chatroom_num) {
		dao.deleteChatRoom(chatroom_num);
	}

	public ChatMemberDTO memberExceptStatus(Map<String, Integer> paramMap) {
		ChatMemberDTO dto = dao.memberExceptStatus(paramMap);
		return dto;
	}

	public ChatMemberDTO selectMember(Integer member_num) {
		ChatMemberDTO dto = dao.selectMember(member_num);
		return dto;
	}


}
