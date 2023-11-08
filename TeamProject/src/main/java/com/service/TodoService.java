package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.LoginConstant;
import com.dao.TodoDAO;
import com.dto.ExcelDTO;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.dto.TodoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TodoService {
	@Autowired
	TodoDAO dao;

//프로젝트에 맞는 나의 todoList 가져오기
	public List<TodoDTO> getAllTodoList(Map<String, Object> parameters) {
		int project_num = (int) parameters.get("project_num"); // 프로젝트 번호 파싱
		Map<String, Integer> map = new HashMap<>();
		map.put("project_num", project_num);

		List<TodoDTO> todoList = dao.getAllTodoList(map);
		return todoList;
	}

	public TodoDTO createTodo(Map<String, Object> parameters) {
		int project_num = (int) parameters.get("project_num");
		Map<String, Object> map = (Map<String, Object>) parameters.get("todoDTO");
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDTO todoDTO = objectMapper.convertValue(map, TodoDTO.class);

		dao.createTodo(todoDTO); // todoDTO에 todo_num의 값이 부여됨
		int todo_num = todoDTO.getTodo_num();
		addProjectTodo(project_num, todo_num);

		return todoDTO;

	}

	public void addProjectTodo(int project_num, int todo_num) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("project_num", project_num);
		map.put("todo_num", todo_num);
		dao.addProjectTodo(map);
	}

	public TodoDTO updateTodo(Map<String, Object> parameters) {
		Map<String, Object> tempMap = (Map<String, Object>) parameters.get("todoDTO");
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDTO todoDTO = objectMapper.convertValue(tempMap, TodoDTO.class);
		dao.updateTodo(todoDTO);
		return todoDTO;
	}

	public void deleteTodo(Map<String, Object> parameters) {
		Map<String, Object> tempMap = (Map<String, Object>) parameters.get("todoDTO");
		ObjectMapper objectMapper = new ObjectMapper();
		TodoDTO todoDTO = objectMapper.convertValue(tempMap, TodoDTO.class);
		dao.deleteTodo(todoDTO);
	}

	// 엑셀 다운로드 - 수정중
	public List<ExcelDTO> todoListDownload(List<TodoDTO> todoList) {
		System.out.println("=============서비스==============");
		List<ExcelDTO> rtnList = new ArrayList<ExcelDTO>();
		try { // todo List 조회
			ExcelDTO eDTO = null;
			for (TodoDTO todo : todoList) {
				eDTO = new ExcelDTO();
				eDTO.setExcelTitle(todo.getTodo_title());// 제목
				eDTO.setMemeberName(todo.getMember_name());// 멤버이름
				eDTO.setExcelsStartDate(todo.getStart_date());// 시작일
				eDTO.setExcelDueDate(todo.getDue_date());// 종료일
				eDTO.setExcelContent(todo.getContent());// 종료일
				rtnList.add(eDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return rtnList;
	}

	public List<ProjectDTO> getProjectList(int member_num) {
		List<ProjectDTO> projectlist = dao.getProjectList(member_num);
		return projectlist;
	}
}
