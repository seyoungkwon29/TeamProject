package com.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.TodoDTO;

@Repository
public class TodoDAO {
	@Autowired
	SqlSessionTemplate session;

	public List<TodoDTO> getAllTodoList(Map<String, Integer> map) {
		List<TodoDTO> todoList = session.selectList("getAllTodoList",map);
		return todoList;
	}

	public void createTodo(TodoDTO todoDTO) {
		int res = session.insert("createTodo",todoDTO);
		System.out.println("인서트 확인 : " + res);
		
	}

	public void addProjectTodo(Map<String, Integer> map) {
		int res = session.insert("addProjectTodo",map);
		
	}
	
}
