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
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TodoService {
	@Autowired
	TodoDAO dao;
	
//프로젝트에 맞는 나의 todoList 가져오기
	public List<TodoDTO> getAllTodoList(Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num"); //프로젝트 번호 파싱
		String t_key = (String)parameters.get("t_key"); //멤버정보 가져오기
		MemberDTO loginMember = LoginConstant.memberMap.get(t_key);
		int member_num = loginMember.getMember_num();
		
		Map<String,Integer> map = new HashMap<>();
		map.put("project_num", project_num);
		map.put("member_num", member_num);
		
		List<TodoDTO> todoList = dao.getAllTodoList(map);
		return todoList;
	}

	public TodoDTO createTodo(Map<String, Object> parameters) {
		int project_num = (int)parameters.get("project_num");
		Map<String,Object> map = (Map<String,Object>)parameters.get("todoDTO");
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDTO todoDTO = objectMapper.convertValue(map, TodoDTO.class);
		
		System.out.println("todoDTO : "+todoDTO);
		dao.createTodo(todoDTO); //todoDTO에 todo_num의 값이 부여됨
		int todo_num = todoDTO.getTodo_num();
		addProjectTodo(project_num, todo_num);
		
		return todoDTO;
		
	}
	public void addProjectTodo(int project_num, int todo_num) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("project_num", project_num);
		map.put("todo_num", todo_num);
		dao.addProjectTodo(map);
	}

	public TodoDTO updateTodo(Map<String, Object> parameters) {
		
		Map<String,Object> tempMap = (Map<String,Object>)parameters.get("todoDTO");
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDTO todoDTO = objectMapper.convertValue(tempMap, TodoDTO.class);
		dao.updateTodo(todoDTO);
		return todoDTO;
	}

	public void deleteTodo(Map<String, Object> parameters) {
		Map<String,Object> tempMap = (Map<String,Object>)parameters.get("todoDTO");
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDTO todoDTO = objectMapper.convertValue(tempMap, TodoDTO.class);
		dao.deleteTodo(todoDTO);
	}
	
}
