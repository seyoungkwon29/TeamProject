package com.controller.projectmanagement;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dto.MemberDTO;
import com.dto.ProjectManagementDTO;
import com.service.ProjectManagementService;

@Controller
public class TimeLineViewController {

	@GetMapping("/timeline")
	public String viewList() {
		System.out.println("타임라인 호출");
		return "projectManagement/TimeLine";
	}
}
