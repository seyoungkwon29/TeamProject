package com.controller.download;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.ExcelDownload;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.dto.TodoDTO;
import com.service.TodoService;

@Controller
@RequestMapping("/download")
public class DownloadCTRL {
	
	@Autowired
	TodoService service;
	
//엑셀다운로드 투두 리스트
	@GetMapping("/todo/excel")
	public void todoListDownload(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO memberDto = (MemberDTO)request.getSession().getAttribute("login");
		Map<String, Object> parameters = new HashMap<String, Object>(){{
			put("t_key", memberDto.getT_key());
		}}; 
		List<TodoDTO> todoList = service.getAllTodoList(parameters);//todolist 검색
		List<ProjectDTO> projectList = service.getProjectList(memberDto.getMember_num());
		System.out.println("===============fileBoardTitleListExcel===================");
		String localNow = DateTimeFormatter.ofPattern("yyyy_MM_dd").format(LocalDateTime.now());
		System.out.println(localNow);
		ExcelDownload.excelProjectTodo(projectList, service.todoListDownload(todoList), "projectTODO_["+localNow+"].xlsx", response);
		System.out.println("==============fileBoardTitleListExcel end==============");
	}

	
	
	
	
}
