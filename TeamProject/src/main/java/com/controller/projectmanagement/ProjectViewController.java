package com.controller.projectmanagement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constant.LoginConstant;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.service.ProjectService;

@Controller
public class ProjectViewController {
	@Autowired
	ProjectService service;
	
	@GetMapping("/projects")
	public String viewList() {
		System.out.println("캘린더 호출");
		return "projectManagement/projectManagement";
	}
	
}
