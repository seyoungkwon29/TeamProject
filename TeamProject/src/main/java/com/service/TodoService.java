package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.LoginConstant;
import com.dao.TodoDAO;
import com.dto.MemberDTO;
import com.dto.TodoDTO;

@Service
public class TodoService {
	@Autowired
	TodoDAO dao;
	
//프로젝트에 맞는 나의 todoList 가져오기
	public List<TodoDTO> getAllTodoList(Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num"); //프로젝트 번호 파싱
		String tKey = (String)parameters.get("tKey"); //멤버정보 가져오기
		MemberDTO loginMember = LoginConstant.memberMap.get(tKey);
		int member_num = loginMember.getMember_num();
		
		Map<String,Integer> map = new HashMap<>();
		map.put("project_num", project_num);
		map.put("member_num", member_num);
		
		List<TodoDTO> todoList = dao.getAllTodoList(map);
		return todoList;
	}
}
