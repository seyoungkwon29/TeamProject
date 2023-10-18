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
	
}
