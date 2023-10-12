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
public class ProjectManagementController {

	@Autowired
	ProjectManagementService pmService;
	
	// 사원번호를 통해 해당 사원이 참여한 프로젝트 넘버에 해당하는 리스트로 호출
//	@GetMapping("/viewList")
//	public List<ProjectManagementDTO> viewList(HttpSession session) {
//		System.out.println("캘린더 호출");
//		MemberDTO member = (MemberDTO) session.getAttribute("login");
//		int member_num = member.getMember_num();
//		return pmService.viewList(member_num);
//	}
	
	@GetMapping("/viewList")
	public String viewList() {
		System.out.println("캘린더 호출");
		return "projectManagement/projectManagement";
	}
}
