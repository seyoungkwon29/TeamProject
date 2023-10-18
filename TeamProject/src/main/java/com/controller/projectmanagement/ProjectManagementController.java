package com.controller.projectmanagement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.constant.LoginConstant;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ProjectService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")

@RequestMapping("/api")
public class ProjectManagementController {
	@Autowired
	ProjectService service;
	
	@GetMapping("/project")
    public List<ProjectDTO> project(@RequestParam Map<String, String> parameters) {
		List<ProjectDTO> list = null;
		String tKey = parameters.get("t_key");
		System.out.println("컨트롤러 실행!");
		if(!StringUtils.hasText(tKey) 
				|| null == LoginConstant.memberMap.get(tKey) 
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			System.out.println("로그인 정보 없음");
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			MemberDTO loginMember = LoginConstant.memberMap.get(tKey);
			System.out.println("로그인 정보:"+loginMember);
			int member_num = loginMember.getMember_num();
			
			list  = service.getAllProject(member_num);
			System.out.println("List 정보 : " + list.get(0));
		}
		
		return list;
    }
	
	@PostMapping("/participatedMemberList")
	public List<MemberDTO> participatedMemberList(@RequestBody Map<String,Object> parameters) {
		List<MemberDTO> memberList = service.participatedMemberList(parameters);
		return memberList;
	}
	
	@PostMapping("/createProject")
	public void createProject (@RequestBody Map<String,Object> parameters) { //JSON형태로 들어오는 데이터를 Map으로 접근
		service.createProject(parameters);
	}
	
	@PostMapping("/updateProject")
	public void updateProject(@RequestBody Map<String,Object> parameters) {
		service.updateProject(parameters);
	}
	
	@PostMapping("/deleteProject")
	public void deleteProject(@RequestBody Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num");
		System.out.println(project_num);
		service.deleteProject(project_num);
		System.out.println(project_num);
	}
	
	
	
}
