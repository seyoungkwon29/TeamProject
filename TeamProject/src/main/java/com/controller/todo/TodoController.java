package com.controller.todo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.TodoDTO;
import com.service.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TodoController {

	@Autowired
	TodoService service;
	
	@PostMapping("/getAllTodoList")
	public List<TodoDTO> getAllTodoList(@RequestBody Map<String,Object> parameters){
		List<TodoDTO> todoList = service.getAllTodoList(parameters);
		return todoList;
	}
	
	@PostMapping("/createTodo")
	public TodoDTO createTodo(@RequestBody Map<String,Object> parameters) {
		System.out.println("create>>"+parameters);
		TodoDTO todoDTO = service.createTodo(parameters);
		return todoDTO;
	}
	
	@PutMapping("/updateTodo")
	public TodoDTO updateTodo(@RequestBody Map<String,Object> parameters) {
		System.out.println("updateTodo>>>"+parameters);
		TodoDTO todoDTO = service.updateTodo(parameters);
		System.out.println("todoDTO"+todoDTO);
		return todoDTO;
	}
	
	@PostMapping("/deleteTodo")
	public void deleteTodo(@RequestBody Map<String,Object> parameters) {
		service.deleteTodo(parameters);
	}
}
